package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.mapper.IRowMapper;

import java.util.List;

public interface IAbstractDao<T> {
	List<T> query(String sql, IRowMapper rowMapper, Object... parameters);
	void modifiedData(String sql, Object... parameters) throws Exception;
    Long insertData(String sql, Object... parameters) throws Exception;
    Long count(String sql, Object... parameters);
}
