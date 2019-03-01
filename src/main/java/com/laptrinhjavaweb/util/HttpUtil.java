package com.laptrinhjavaweb.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

public class HttpUtil {
    private String value;

    public HttpUtil(String value) {
        this.value = value;
    }

    public <T> T toModel(Class<T> clazz) {
        T model = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            model = mapper.readValue(value, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model;
    }

    public static HttpUtil of(BufferedReader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new HttpUtil(sb.toString());
    }

    public static void writeValue(OutputStream out, Object value) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(out, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}