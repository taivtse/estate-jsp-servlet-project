package com.laptrinhjavaweb.dao.build.impl;

import com.laptrinhjavaweb.dao.build.IGenericDao;
import com.laptrinhjavaweb.dao.util.JDBCUtil;
import com.laptrinhjavaweb.orm.mapper.IRowMapper;
import com.laptrinhjavaweb.orm.util.IEntityUtil;
import com.laptrinhjavaweb.orm.util.StatementUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
    private Class<T> entityClass;

    private IRowMapper rowMapper;
    private IEntityUtil entityUtil;

    public AbstractDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        rowMapper = IRowMapper.of(entityClass);
        entityUtil = IEntityUtil.of(this.entityClass);
    }


    protected List<T> query(String sql, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            StatementUtil.setParametersToStatement(preparedStatement, parameters);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                results.add((T) rowMapper.mapRow(resultSet));
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

    protected void modifiedData(String sql, Object... parameters) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            StatementUtil.setParametersToStatement(preparedStatement, parameters);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    protected Long save(T entity) throws Exception {
        String sql = entityUtil.buildInsertStatement();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long generateId = null;
        try {
            connection = JDBCUtil.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            StatementUtil.setEntityToStatement(preparedStatement, entity);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                generateId = resultSet.getLong(1);
            }
            connection.commit();
            return generateId;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw e;
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
                throw e;
            }
        }
    }

    protected Long count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Long count = 0L;
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement(sql);
            StatementUtil.setParametersToStatement(statement, parameters);
            resultSet = statement.executeQuery();
            resultSet.next();
            count = resultSet.getLong(1);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0L;
            }
        }
    }

    @Override
    public List<T> findAll() {
        String sql = entityUtil.buildSelectStatement();
        return this.query(sql);
    }

    @Override
    public T findById(ID id) {
        String sql = entityUtil.buildSelectByIdStatement();
        List<T> resultList = this.query(sql, id);
        return resultList.size() == 1 ? resultList.get(0) : null;
    }

}
