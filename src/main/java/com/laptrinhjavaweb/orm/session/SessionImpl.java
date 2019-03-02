package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.annotation.IdField;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.Restriction;
import com.laptrinhjavaweb.orm.criteria.statement.NamedParamStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionImpl implements Session {
    private Connection connection;

    public SessionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T, ID> T get(Class<T> entityClass, ID id) {
        String idFieldName = entityClass.getAnnotation(IdField.class).name();
        Criteria criteria = this.createCriteria(entityClass);
        criteria.addRestriction(Restriction.and().eq(idFieldName, id));
        return (T) criteria.uniqueResult();
    }

    @Override
    public <T> void save(T entity) {

    }

    @Override
    public <T> void update(T entity) {

    }

    @Override
    public <T> void delete(T entity) {

    }

    @Override
    public <T> Criteria createCriteria(Class<T> entityClass) {
        return new CriteriaImpl(connection, entityClass);
    }

    private void closeAllAfterExecute(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (resultSet != null) {
            resultSet.close();
        }
    }

    private <T> void setEntityToStatement(T entity, NamedParamStatement statement) throws Exception {
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

            statement.setParameter(fieldName, fieldData);
        }
    }
}
