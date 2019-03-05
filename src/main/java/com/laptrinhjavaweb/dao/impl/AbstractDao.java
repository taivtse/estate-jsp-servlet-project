package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.orm.query.criteria.Criteria;
import com.laptrinhjavaweb.orm.query.criteria.criterion.Criterion;
import com.laptrinhjavaweb.orm.query.criteria.criterion.projection.Projections;
import com.laptrinhjavaweb.orm.session.Session;
import com.laptrinhjavaweb.orm.session.SessionFactory;
import com.laptrinhjavaweb.orm.transaction.Transaction;
import com.laptrinhjavaweb.orm.util.EntityUtil;
import com.laptrinhjavaweb.orm.util.ObjectAccessUtil;
import com.laptrinhjavaweb.paging.Pageable;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

public class AbstractDao<T, ID> implements GenericDao<T, ID> {
    @Inject
    private SessionFactory sessionFactory;
    private Class<T> entityClass;

    public AbstractDao() {
        // set persistenceClass = T
        Type type = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        entityClass = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return SessionFactory.openSession();
    }

    @Override
    public List<T> findAll() {
        List<T> entityList;
        Session session = this.getSession();
        entityList = session.createCriteria(this.entityClass).list();
        session.close();
        return entityList;
    }

    @Override
    public List<T> findAllByProperties(Pageable pageable, List<Criterion> criterionList) {
        List<T> entityList;
        Session session = this.getSession();
        Criteria criteria = session.createCriteria(this.entityClass);

        if (pageable != null) {
//            set start position offset
            if (pageable.getOffset() != null && pageable.getOffset() >= 0) {
                criteria.setFirstResult(pageable.getOffset());
            }

//            set limit row
            if (pageable.getLimit() != null && pageable.getLimit() >= 0) {
                criteria.setMaxResults(pageable.getLimit());
            }

//            set sorter
            if (pageable.getSorter() != null
                    && !pageable.getSorter().getPropertyName().isEmpty()
                    && !pageable.getSorter().getDirection().isEmpty()) {
                criteria.addOrder(pageable.getSorter().getOrder());
            }
        }

//        set properties search
        if (criterionList != null) {
            criterionList.forEach(criterion -> criteria.add(criterion));
        }

        entityList = criteria.list();
        session.close();

        return entityList;
    }

    @Override
    public Long countByProperties(List<Criterion> criterionList) {
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.entityClass);

//        set properties search
        if (criterionList != null) {
            criterionList.forEach(criterion -> cr.add(criterion));
        }

        cr.setProjection(Projections.rowCount());
        return (Long) cr.uniqueResult();
    }

    @Override
    public T findOneById(ID id) {
        Session session = this.getSession();
        T entity = session.get(this.entityClass, id);
        session.close();
        return entity;
    }

    @Override
    public T findOneByProperties(List<Criterion> criterionList) {
        T entity;
        Session session = this.getSession();
        Criteria cr = session.createCriteria(this.entityClass);
        if (criterionList != null) {
            criterionList.forEach(criterion -> cr.add(criterion));
        }
        entity = (T) cr.uniqueResult();
        session.close();
        return entity;
    }

    @Override
    public void save(T entity) throws SQLException {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(T entity) throws SQLException {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(T entity) throws SQLException {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(entity);
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteById(ID id) throws SQLException {
        try {
            T entity = entityClass.newInstance();
            String idFieldName = EntityUtil.getIdFieldName(entityClass);
            Field idField = ObjectAccessUtil.getFieldByName(entityClass, idFieldName);
            ObjectAccessUtil.setFieldData(entity, id, idField);

            this.delete(entity);
        } catch (InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

}
