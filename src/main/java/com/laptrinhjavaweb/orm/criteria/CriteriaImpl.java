package com.laptrinhjavaweb.orm.criteria;

import com.laptrinhjavaweb.orm.builder.QueryBuilder;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CriteriaImpl implements Criteria {
    private Connection connection;
    private Class entityClass;
    private NamedParamQuery query;

    public CriteriaImpl(Connection connection, Class entityClass) {
        this.connection = connection;
        this.entityClass = entityClass;
        query = new NamedParamQuery(connection);
    }

    @Override
    public List list() {
        ResultSet resultSet = null;
        List resultList = new ArrayList<>();
        try {
            String sql = QueryBuilder.of(this.entityClass).buildSelectQuery();
            this.query.setSqlQuery(sql);

            resultSet = this.query.getPreparedStatement().executeQuery();

            while (resultSet.next()) {
                resultList.add(EntityMapper.of(this.entityClass).toEntity(resultSet));
            }

            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAfterQuery(this.connection, this.query.getPreparedStatement(), resultSet);
        }
        return null;
    }

    private <T> void setEntityToQuery(T entity) throws Exception {
        Class entityClass = entity.getClass();
        Field[] fieldList = entityClass.getDeclaredFields();

        for (Field field : fieldList) {
            String fieldName = field.getName();
            String getterMethodName = "get" + StringUtils.capitalize(fieldName);

            Method getterMethod = entityClass.getMethod(getterMethodName);
            Object fieldData = getterMethod.invoke(entity);

            query.setParam(fieldName, fieldData);
        }
    }

    private void closeAfterQuery(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (this.connection != null) {
                this.connection.close();
            }
            if (this.query.getPreparedStatement() != null) {
                this.query.getPreparedStatement().close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
