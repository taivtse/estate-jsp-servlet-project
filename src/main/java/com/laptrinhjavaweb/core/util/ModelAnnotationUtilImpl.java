package com.laptrinhjavaweb.core.util;

import com.laptrinhjavaweb.core.anotation.Column;
import com.laptrinhjavaweb.core.anotation.Id;

import java.lang.reflect.Field;

public class ModelAnnotaionUtilImpl implements ModelAnnotationUtil {
    private Object model;

    public ModelAnnotaionUtilImpl(Object model) {
        this.model = model;
    }

    @Override
    public String getIdColumnName() {
        Field[] fieldList = this.model.getClass().getDeclaredFields();
        for (Field field : fieldList) {
            if (field.isAnnotationPresent(Id.class)) {
                String name = field.getAnnotation(Column.class).name();
                return name;
            }
        }
        return null;
    }
}
