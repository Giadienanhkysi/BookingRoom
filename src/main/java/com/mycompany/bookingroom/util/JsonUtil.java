package com.mycompany.bookingroom.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class JsonUtil {
    private String value;

    public JsonUtil(String value) {
        this.value = value;
    }
    
    public <T> T toModel(Class<T> valueType){
        /**
         * chuyển chuỗi sang json
         */
        try {
            T model = new ObjectMapper().readValue(value, valueType);
            return model;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public <T> List<T> toListModel(Class<T> valueType){
        /**
         * chuyển chuỗi sang json
         */
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<T> models = mapper.readValue(value, mapper.getTypeFactory().constructCollectionType(List.class, valueType));
            return models;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static JsonUtil toJsonUtil(BufferedReader reader){
        /**
        đọc dữ liệu chuyển thành chuỗi json
         */
        StringBuilder json = new StringBuilder();
        String line;
        try {
            while((line = reader.readLine()) != null){
                json.append(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(JsonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JsonUtil(json.toString());
    }
}
