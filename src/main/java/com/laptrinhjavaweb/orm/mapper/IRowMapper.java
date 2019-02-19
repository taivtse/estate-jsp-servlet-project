package com.laptrinhjavaweb.orm.mapper;

import java.sql.ResultSet;

public interface IRowMapper {
    RowMapperImpl rowMapper = new RowMapperImpl();

    Object mapRow(ResultSet resultSet) throws Exception;

    static IRowMapper of(Class<?> entityClass) {
        rowMapper.setEntityClass(entityClass);
        return rowMapper;
    }
}
