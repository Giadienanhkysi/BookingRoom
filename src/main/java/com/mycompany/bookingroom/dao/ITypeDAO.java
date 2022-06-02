package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Type;

public interface ITypeDAO {
    List<Type> findAll();
    Type findById(Integer id);
    Integer save(Type type);
    void update(Type type);    
    void delete(Integer id);
}
