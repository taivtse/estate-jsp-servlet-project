package com.laptrinhjavaweb.dao.build;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable> {
    //    common built methods
    List<T> findAll();

    T findById(ID id);
}
