package com.laptrinhjavaweb.orm.session;

import com.laptrinhjavaweb.orm.annotation.IdField;
import com.laptrinhjavaweb.orm.builder.StatementBuilder;
import com.laptrinhjavaweb.orm.criteria.Criteria;
import com.laptrinhjavaweb.orm.criteria.CriteriaImpl;
import com.laptrinhjavaweb.orm.criteria.criterion.Restrictions;
import com.laptrinhjavaweb.orm.session.util.CloseExecutorUtil;
import com.laptrinhjavaweb.orm.statement.NamedParamStatement;
import com.laptrinhjavaweb.orm.transaction.Transaction;
import com.laptrinhjavaweb.orm.transaction.TransactionImpl;
import com.laptrinhjavaweb.orm.util.EntityUtil;
import com.laptrinhjavaweb.orm.util.ObjectAccessUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionImpl implements Session {
    private Connection connection;
    NamedParamStatement statement;

    public SessionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <T, ID> T get(Class<T> entityClass, ID id) {
        String idFieldName = entityClass.getAnnotation(IdField.class).name();
        Criteria criteria = this.createCriteria(entityClass);
        criteria.add(Restrictions.and().eq(idFieldName, id));
        return (T) criteria.uniqueResult();
    }

    @Override
    public <T> void save(T entity) throws SQLException {
        String sql = StatementBuilder.buildInsertStatement(entity.getClass());
        statement = new NamedParamStatement(connection, sql);
        this.setEntityToStatement(entity, statement);
        Long generateId = statement.executeInsert();

//            lấy lại giá trị của entity trong trường hợp có những giá trị do trigger sinh ra.
        if (generateId != null) {
            entity = (T) this.get(entity.getClass(), generateId);
        } else {
            Object id = EntityUtil.getIdFieldData(entity);
            entity = (T) this.get(entity.getClass(), id);
        }
    }

    @Override
    public <T> void update(T entity) throws SQLException {
        String sql = StatementBuilder.buildUpdateStatement(entity.getClass());
        statement = new NamedParamStatement(connection, sql);
        this.setEntityToStatement(entity, statement);
        Integer rowsEffect = statement.executeUpdate();

//            lấy lại giá trị của entity trong trường hợp có những giá trị do trigger sinh ra.
        if (rowsEffect > 0) {
            Object id = EntityUtil.getIdFieldData(entity.getClass(), entity);
            entity = (T) this.get(entity.getClass(), id);
        }
    }

    @Override
    public <T> void delete(T entity) throws SQLException {
        //        lấy tên của field id và giá trị của id để set param cho câu statement
        Object id = EntityUtil.getIdFieldData(entity.getClass(), entity);
        String idFieldName = EntityUtil.getIdFieldName(entity.getClass());

        String sql = StatementBuilder.buildDeleteStatement(entity.getClass());
        statement = new NamedParamStatement(connection, sql);
        statement.setParameter(idFieldName, id);
        statement.executeUpdate();
    }

    @Override
    public <T> Criteria createCriteria(Class<T> entityClass) {
        return new CriteriaImpl(connection, entityClass);
    }

    @Override
    public Transaction beginTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            CloseExecutorUtil.closeAllAfterExecute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private <T> void setEntityToStatement(T entity, NamedParamStatement statement) throws SQLException {
        Class entityClass = entity.getClass();
        Field[] fieldList = entityClass.getDeclaredFields();

        for (Field field : fieldList) {
            Object fieldData;
            try {
                fieldData = ObjectAccessUtil.getFieldData(entity, field);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            statement.setParameter(field.getName(), fieldData);
        }
    }
}
