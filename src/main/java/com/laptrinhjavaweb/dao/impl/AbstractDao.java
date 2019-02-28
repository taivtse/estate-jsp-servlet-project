package com.laptrinhjavaweb.dao.impl;

import com.laptrinhjavaweb.dao.GenericDao;
import com.laptrinhjavaweb.orm.session.Session;
import com.laptrinhjavaweb.orm.session.SessionFactory;

import javax.inject.Inject;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class AbstractDao<T, ID extends Serializable> implements GenericDao<T, ID> {
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
        return this.sessionFactory.openSession();
    }

    @Override
    public List<T> findAll() {
        List<T> entityList;
        Session session = this.getSession();
        entityList = session.createQuery(this.entityClass).list();
        return entityList;
    }

    @Override
    public T findOneById(ID id) {
        Session session = this.getSession();
        return session.findOneById(this.entityClass, id);
    }

}
