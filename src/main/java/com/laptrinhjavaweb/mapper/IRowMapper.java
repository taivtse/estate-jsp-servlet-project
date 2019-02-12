package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

public interface IRowMapper {
	Object mapRow(ResultSet resultSet) throws Exception;

	static IRowMapper of(Class<?> clazz){
	    return new RowMapperImpl(clazz);
    }
}
