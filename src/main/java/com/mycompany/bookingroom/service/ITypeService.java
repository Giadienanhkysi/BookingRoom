package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Type;

public interface ITypeService {

    List<Type> findAll();

    Type findById(Integer id);

    Integer save(Type yype);

    void update(Type type);

    void delete(Integer id);
}
