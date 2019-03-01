package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.annotation.IdField;
import com.laptrinhjavaweb.orm.builder.QueryBuilder;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.NamedParamStatement;
import com.laptrinhjavaweb.orm.mapper.EntityMapper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public <T, ID> T findOneById(Class<T> entityClass, ID id) {
        ResultSet resultSet = null;
        Object result = null;
        try {
            String sql = QueryBuilder.of(entityClass).buildSelectByIdQuery();
            NamedParamStatement statement = new NamedParamStatement(this.connection);
            statement.setSqlStatement(sql);

            String idFieldName = entityClass.getAnnotation(IdField.class).name();
            statement.setParam(idFieldName, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = EntityMapper.of(entityClass).toEntity(resultSet);
            }

            return (T) result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
}
