package com.laptrinhjavaweb.orm.criteria.util;

import com.laptrinhjavaweb.orm.statement.NamedParam;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedParamUtil {
    public static void handleNamedParamProperty(Map<String, NamedParam> namedParamMap, NamedParam namedParam) {
        String propertyName = namedParam.getPropertyName();

        if (namedParamMap.containsKey(propertyName)) {
            AtomicInteger nextWhereId = new AtomicInteger(1);

            String finalPropertyName = propertyName;
            namedParamMap.forEach((key, simpleExpression) -> {
                if (key.startsWith(finalPropertyName)) {
//                    get index of "_" character
                    int separatorIndex = key.lastIndexOf("_");

                    if (separatorIndex != -1) {
                        int curWhereId = Integer.parseInt(key.substring(separatorIndex + 1));
                        if (curWhereId >= nextWhereId.get()) {
                            nextWhereId.set(curWhereId + 1);
                        }
                    }
                }
            });

//            set new property name to avoid multiple property name in a query
            propertyName = propertyName + "_" + nextWhereId;
            namedParam.setPropertyName(propertyName);
        }

        namedParamMap.put(propertyName, namedParam);
    }

    public static NamedParam createNewNamedParam(Map<String, NamedParam> namedParamMap, String propertyName, Object value) {
        NamedParam namedParam = new NamedParam(propertyName, value);
        NamedParamUtil.handleNamedParamProperty(namedParamMap, namedParam);

        return namedParam;
    }
}
