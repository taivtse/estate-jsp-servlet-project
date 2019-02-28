package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;
import com.laptrinhjavaweb.orm.statement.OrmStatementUtil;

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
        Criteria criteria = new CriteriaImpl(connection, entityClass);
        return criteria.list();
    }

    @Override
    public <T, ID extends Serializable> T findOneById(Class<T> entityClass, ID id) {
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

    protected <T> List<T> executeQuery(Class<T> entityClass, String sql, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
//            connection = SessionFactory.openSession();
            preparedStatement = connection.prepareStatement(sql);
            OrmStatementUtil.setParametersToStatement(preparedStatement, parameters);
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
