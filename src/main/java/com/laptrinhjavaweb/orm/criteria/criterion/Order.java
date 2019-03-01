package com.laptrinhjavaweb.orm.criteria.criterion;

public class Order {
    public static String asc(String propertyName){
        return "ORDER BY " + propertyName + " ASC";
    }

    public static String desc(String propertyName){
        return "ORDER BY " + propertyName + " DESC";
    }
}
