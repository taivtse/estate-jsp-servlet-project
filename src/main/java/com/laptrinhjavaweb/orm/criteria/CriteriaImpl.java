package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.SimpleExpression;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;
import com.laptrinhjavaweb.orm.util.EntityUtil;
import com.laptrinhjavaweb.orm.util.CloseExecutorUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CriteriaImpl implements Criteria {
    private Class entityClass;
    private NamedParamStatement statement;

    String genericQuery = "SELECT {selectColumns} FROM {tableName}{where}{groupBy}{having}{orderBy}{limit}{offset}";
    String selectColumns = "";
    String tableName = "";
    StringBuilder where = new StringBuilder();
    String groupBy = "";
    String having = "";
    String orderBy = "";
    String limit = "";
    String offset = "";

    Map<String, SimpleExpression> expressionMap = new TreeMap<>();

    public CriteriaImpl(Connection connection, Class entityClass) {
        this.entityClass = entityClass;
        statement = new NamedParamStatement(connection);
        this.selectColumns = "*";
        this.tableName = EntityUtil.of(entityClass).getTableName();
    }

    @Override
    public List list() {
        ResultSet resultSet = null;
        List resultList = new ArrayList<>();
        try {
            resultSet = this.executeQuery();
            while (resultSet.next()) {
                resultList.add(EntityMapper.of(this.entityClass).toEntity(resultSet));
            }

            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            CloseExecutorUtil.closeAllAfterExecute(this.statement, resultSet);
        }
    }

    @Override
    public Object uniqueResult() {
        ResultSet resultSet = null;
        Object object = null;
        try {
            this.setMaxResults(1);
            this.setFirstResult(0);
            resultSet = this.executeQuery();

            if (resultSet.next()) {
                object = EntityMapper.of(this.entityClass).toEntity(resultSet);
            }

            return object;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            CloseExecutorUtil.closeAllAfterExecute(this.statement, resultSet);
        }
    }

    @Override
    public Criteria addEntity(Class entityClass) {
        return this;
    }

    @Override
    public Criteria addSelectField(String fieldName) {
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
    public Criteria addWhere(SimpleExpression expression) {
        this.addExpression(expression);

        if (where.length() == 0) {
            where.append(" WHERE ");
        } else {
            where.append(" AND ");
        }

        where.append(expression.toSqlString());
        return this;
    }

    @Override
    public Criteria addOrder(String order) {
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

    private void addExpression(SimpleExpression expression) {
        String propertyName = expression.getPropertyName();

        if (this.expressionMap.containsKey(propertyName)) {
            AtomicInteger nextWhereId = new AtomicInteger(1);

            String finalPropertyName = propertyName;
            this.expressionMap.forEach((key, simpleExpression) -> {
                if (key.startsWith(finalPropertyName)) {
//                    get index of "_" character
                    int separatorIndex = key.lastIndexOf("_");

                    if (separatorIndex != -1) {
                        int curWhereId = Integer.parseInt(key.substring(separatorIndex + 1));
                        if (curWhereId >= nextWhereId.get()) {
                            nextWhereId.set(curWhereId + 1);
                        }
                    }
                }
            });

//            set new property name to avoid multiple property name in a query
            propertyName = propertyName + "_" + nextWhereId;
        }

        this.expressionMap.put(propertyName, expression);

//            set column name for expression
        String columnName = EntityUtil.of(entityClass).getColumnName(expression.getPropertyName());
        expression.setColumnName(columnName);
        expression.setPropertyName(propertyName);
    }

    public ResultSet executeQuery() {
        ResultSet resultSet;
        List resultList = null;
        try {
            String sql = this.handleGenericQuery();
            this.statement.setSqlStatement(sql);
            this.statement.setParamMap(this.expressionMap);

            resultSet = this.statement.executeQuery();

            return resultSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
