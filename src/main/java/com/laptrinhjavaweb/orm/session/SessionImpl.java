package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.annotation.IdField;
import com.laptrinhjavaweb.orm.builder.QueryBuilder;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.NamedParamQuery;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SessionImpl implements Session {
    private Connection connection;

    public SessionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        Criteria criteria = this.createQuery(entityClass);
        return criteria.list();
    }

    @Override
    public <T, ID extends Serializable> T findOneById(Class<T> entityClass, ID id) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        Object result = null;
        try {
            String sql = QueryBuilder.of(entityClass).buildSelectByIdQuery();
            NamedParamQuery query = new NamedParamQuery(this.connection);
            query.setSqlQuery(sql);

            String idFieldName = entityClass.getAnnotation(IdField.class).name();
            query.setParam(idFieldName, id);

            preparedStatement = query.getPreparedStatement();

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = EntityMapper.of(entityClass).toEntity(resultSet);
            }

            return (T) result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeAfterQuery(this.connection, preparedStatement, resultSet);
        }
        return null;
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
    public <T> Criteria createQuery(Class<T> entityClass) {
        return new CriteriaImpl(connection, entityClass);
    }

    private void closeAfterQuery(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected <T> List<T> executeQuery(Class<T> entityClass, String sql, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = SessionFactory.openSession();
            preparedStatement = connection.prepareStatement(sql);
//            OrmStatementUtil.setParametersToStatement(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.add((T) EntityMapper.of(entityClass).toEntity(resultSet));
            }

            return results;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
