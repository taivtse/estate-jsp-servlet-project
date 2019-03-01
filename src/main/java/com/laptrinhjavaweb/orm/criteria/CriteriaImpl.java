package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.criteria.criterion.SimpleExpression;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;
import com.laptrinhjavaweb.orm.util.EntityUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    Map<String, SimpleExpression> whereExpressionMap = new TreeMap<>();

    public CriteriaImpl(Connection connection, Class entityClass) {
        this.entityClass = entityClass;
        statement = new NamedParamStatement(connection);
        this.selectColumns = "*";
        this.tableName = EntityUtil.of(entityClass).getTableName();
    }

    @Override
    public List list() {
        ResultSet resultSet = null;
        List resultList = null;
        try {
            String sql = this.processGenericQuery();
            this.statement.setSqlStatement(sql);

            whereExpressionMap.forEach((key, simpleExpression) -> {
                this.statement.setParam(key, simpleExpression.getValue());
            });

            resultSet = this.statement.executeQuery();

            if (resultSet.isBeforeFirst()){
                 resultList = new ArrayList<>();

                while (resultSet.next()) {
                    resultList.add(EntityMapper.of(this.entityClass).toEntity(resultSet));
                }
            }

            return resultList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                this.closeAllAfterQuery(resultSet);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Override
    public Object uniqueResult() {
        return null;
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
        String propertyName = expression.getPropertyName();

        propertyName = this.getNextWhereNamedParam(propertyName);

        whereExpressionMap.put(propertyName, expression);

        if (where.length() == 0) {
            where.append(" WHERE ");
        } else {
            where.append(" AND ");
        }

//            set column name for expression
        String columnName = EntityUtil.of(entityClass).getColumnName(expression.getPropertyName());
        expression.setColumnName(columnName);
        expression.setPropertyName(propertyName);

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
    public Criteria setLimit(int limit) {
        this.limit = " LIMIT " + limit;
        return this;
    }

    @Override
    public Criteria setOffset(int offset) {
        this.offset = " OFFSET " + offset;
        return this;
    }

    private <T> void setEntityToQuery(T entity) throws Exception {
        Class entityClass = entity.getClass();
        Field[] fieldList = entityClass.getDeclaredFields();

        for (Field field : fieldList) {
            String fieldName = field.getName();
//            upper case the first letter of field name
            fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

//            build getter method name
            String getterMethodName = "get" + fieldName;

            Method getterMethod = entityClass.getMethod(getterMethodName);
            Object fieldData = getterMethod.invoke(entity);

            statement.setParam(fieldName, fieldData);
        }
    }

    private String processGenericQuery() {
        this.genericQuery = genericQuery.replace("{selectColumns}", selectColumns);
        this.genericQuery = genericQuery.replace("{tableName}", tableName);
        this.genericQuery = genericQuery.replace("{where}", where);
        this.genericQuery = genericQuery.replace("{groupBy}", groupBy);
        this.genericQuery = genericQuery.replace("{having}", having);
        this.genericQuery = genericQuery.replace("{orderBy}", orderBy);
        this.genericQuery = genericQuery.replace("{limit}", limit);
        this.genericQuery = genericQuery.replace("{offset}", offset);
        return genericQuery;
    }

    private void closeAllAfterQuery(ResultSet resultSet) throws SQLException {
        if (statement.getConnection() != null) {
            statement.getConnection().close();
        }
        if (statement.getPreparedStatement() != null) {
            statement.getPreparedStatement().close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private String getNextWhereNamedParam(String propertyName) {
        if (whereExpressionMap.containsKey(propertyName)) {
            AtomicInteger nextWhereId = new AtomicInteger(1);

            String finalPropertyName = propertyName;
            whereExpressionMap.forEach((key, simpleExpression) -> {
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

        return propertyName;
    }
}
