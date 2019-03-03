package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.Order;
import com.laptrinhjavaweb.orm.criteria.criterion.Restriction;
import com.laptrinhjavaweb.orm.statement.NamedParam;
import com.laptrinhjavaweb.orm.statement.NamedParamStatement;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;
import com.laptrinhjavaweb.orm.session.util.CloseExecutorUtil;
import com.laptrinhjavaweb.orm.util.EntityUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CriteriaImpl implements Criteria {
    private Connection connection;
    private Class entityClass;
    private NamedParamStatement statement;

    private String genericQuery = "SELECT {selectColumns} FROM {tableName}{where}{groupBy}{having}{orderBy}{limit}{offset}";
    private String selectColumns = "";
    private String tableName = "";
    private StringBuilder where = new StringBuilder(" WHERE 1=1");
    private String groupBy = "";
    private String having = "";
    private String orderBy = "";
    private String limit = "";
    private String offset = "";

    private Map<String, NamedParam> namedParamMap = new TreeMap<>();

    public CriteriaImpl(Connection connection, Class entityClass) {
        this.connection = connection;
        this.entityClass = entityClass;
        this.selectColumns = "*";
        this.tableName = EntityUtil.of(entityClass).getTableName();
    }

    @Override
    public Class getEntityClass() {
        return this.entityClass;
    }

    @Override
    public Map<String, NamedParam> getNamedParamMap() {
        return namedParamMap;
    }

    @Override
    public List list() {
        ResultSet resultSet;
        List resultList = new ArrayList<>();
        try {
            resultSet = this.executeQuery();
            while (resultSet.next()) {
                resultList.add(EntityMapper.of(this.entityClass).toEntity(resultSet));
            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }

        return resultList;
    }

    @Override
    public Object uniqueResult() {
        ResultSet resultSet;
        Object object = null;
        try {
            this.setMaxResults(1);
            this.setFirstResult(0);
            resultSet = this.executeQuery();

            if (resultSet.next()) {
                object = EntityMapper.of(this.entityClass).toEntity(resultSet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.close();
        }

        return object;
    }

    @Override
    public Criteria addSelection(String fieldName) {
        if (this.selectColumns.equals("*")) {
            this.selectColumns = "";
        }
        if (!this.selectColumns.equals("*") && !this.selectColumns.equals("")) {
            this.selectColumns += ", ";
        }

        this.selectColumns += EntityUtil.of(entityClass).getColumnName(fieldName);
        return this;
    }

    @Override
    public Criteria addRestriction(Restriction restriction) {
        where.append(restriction.getPrefixLogical());
        where.append(restriction.getCriterion().toSqlString(this));
        return this;
    }

    @Override
    public Criteria addOrder(Order order) {
        orderBy = order.getExpression();
        return this;
    }

    @Override
    public Criteria addProjection(String projection) {
        return this;
    }

    @Override
    public Criteria setMaxResults(int limit) {
        this.limit = " LIMIT " + limit;
        return this;
    }

    @Override
    public Criteria setFirstResult(int offset) {
        this.offset = " OFFSET " + offset;
        return this;
    }

    public void close() {
        try {
            CloseExecutorUtil.closeNamedParamStatement(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String handleGenericQuery() {
        this.genericQuery = genericQuery.replace("{selectColumns}", selectColumns);
        this.genericQuery = genericQuery.replace("{tableName}", tableName);
        this.genericQuery = genericQuery.replace("{where}", where);
        this.genericQuery = genericQuery.replace("{groupBy}", groupBy);
        this.genericQuery = genericQuery.replace("{having}", having);
        this.genericQuery = genericQuery.replace("{orderBy}", orderBy);
        this.genericQuery = genericQuery.replace("{limit}", limit);
        this.genericQuery = genericQuery.replace("{offset}", offset);
        return this.genericQuery;
    }

    private ResultSet executeQuery() {
        ResultSet resultSet;
        try {
            String sql = this.handleGenericQuery();
            this.statement = new NamedParamStatement(connection, sql);
            this.statement.setNamedParamMap(namedParamMap);

            resultSet = this.statement.executeQuery();

            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
