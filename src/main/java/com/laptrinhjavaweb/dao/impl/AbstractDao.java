package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.IGenericDao;
import com.laptrinhjavaweb.orm.mapper.IRowMapper;
import com.laptrinhjavaweb.orm.util.IEntityUtil;
import com.laptrinhjavaweb.orm.util.StatementUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
    private Class<T> modelClass;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private IRowMapper rowMapper;
    private IEntityUtil modelAnnotationUtil;

    public AbstractDao() {
        this.modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        rowMapper = IRowMapper.of(modelClass);
        modelAnnotationUtil = IEntityUtil.of(this.modelClass);
    }

    public Connection getConnection() {
        try {
            Class.forName(resourceBundle.getString("db.driver"));
            String url = resourceBundle.getString("db.url");
            String user = resourceBundle.getString("db.username");
            String password = resourceBundle.getString("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<T> query(String sql, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
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

    @Override
    public void modifiedData(String sql, Object... parameters) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
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

    @Override
    public Long save(T entity) throws Exception {
        String sql = modelAnnotationUtil.buildInsertStatement();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long generateId = null;
        try {
            connection = getConnection();
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

    @Override
    public Long count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Long count = 0L;
            connection = getConnection();
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
        String sql = modelAnnotationUtil.buildSelectStatement();
        return this.query(sql);
    }

    @Override
    public T findById(ID id) {
        String sql = modelAnnotationUtil.buildSelectByIdStatement();
        List<T> resultList = this.query(sql, id);
        return resultList.size() == 1 ? resultList.get(0) : null;
    }

}
