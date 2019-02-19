package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.orm.util.IModelAnnotationUtil;
import com.laptrinhjavaweb.dao.IGenericDao;
import com.laptrinhjavaweb.mapper.IRowMapper;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
    private Class<T> modelClass;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private IRowMapper rowMapper;
    private IModelAnnotationUtil modelAnnotationUtil;

    public AbstractDao() {
        this.modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        rowMapper = IRowMapper.of(modelClass);
        modelAnnotationUtil = IModelAnnotationUtil.of(this.modelClass);
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
            this.setParameters(preparedStatement, parameters);
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
            setParameters(preparedStatement, parameters);

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
    public Long save(T model) throws Exception {
        String sql = modelAnnotationUtil.buildInsertStatement();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Long generateId = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.setModelToStatement(preparedStatement, model);
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
            setParameters(statement, parameters);
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

    private void setParameters(PreparedStatement preparedStatement, Object... parameters) throws Exception {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            int index = i + 1;

            this.setParameterAt(index, preparedStatement, parameter);
        }
    }

    private void setModelToStatement(PreparedStatement preparedStatement, Object model) throws Exception {
        Field[] fieldList = model.getClass().getDeclaredFields();
        int index = 1;
        for (Field field : fieldList) {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
//          set data form entity to preparedStatement
            Object fieldData = field.get(model);
            field.setAccessible(accessible);

            this.setParameterAt(index, preparedStatement, fieldData);
            index++;
        }
    }

    private void setParameterAt(int index, PreparedStatement preparedStatement, Object parameter) throws Exception {
        if (parameter instanceof Byte) {
            preparedStatement.setByte(index, (Byte) parameter);

        } else if (parameter instanceof Short) {
            preparedStatement.setShort(index, (Short) parameter);

        } else if (parameter instanceof Integer) {
            preparedStatement.setInt(index, (Integer) parameter);

        } else if (parameter instanceof Long) {
            preparedStatement.setLong(index, (Long) parameter);

        } else if (parameter instanceof Float) {
            preparedStatement.setFloat(index, (Float) parameter);

        } else if (parameter instanceof Double) {
            preparedStatement.setDouble(index, (Double) parameter);

        } else if (parameter instanceof BigDecimal) {
            preparedStatement.setBigDecimal(index, (BigDecimal) parameter);

        } else if (parameter instanceof String) {
            preparedStatement.setString(index, (String) parameter);

        } else if (parameter instanceof Date) {
            preparedStatement.setDate(index, (Date) parameter);

        } else if (parameter instanceof Time) {
            preparedStatement.setTime(index, (Time) parameter);

        } else if (parameter instanceof Timestamp) {
            preparedStatement.setTimestamp(index, (Timestamp) parameter);

        } else if (parameter instanceof InputStream) {
            preparedStatement.setBlob(index, (InputStream) parameter);

        } else if (parameter == null) {
            preparedStatement.setNull(index, Types.NULL);
        } else {
            throw new Exception("Chưa hỗ trợ parameter có kiểu: " + parameter.getClass().getName());
        }
    }
}
