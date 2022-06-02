package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.ITypeDAO;
import com.mycompany.bookingroom.model.Type;
import com.mycompany.bookingroom.service.ITypeService;
import javax.inject.Inject;

public class TypeService implements ITypeService {

    @Inject
    private ITypeDAO typeDAO;


    @Override
    public List<Type> findAll() {
        return typeDAO.findAll();
    }

    @Override
    public Type findById(Integer id) {
        return typeDAO.findById(id);
    }

    @Override
    public Integer save(Type type) {
        return typeDAO.save(type);
    }

    @Override
    public void update(Type type) {
        typeDAO.update(type);
    }

    @Override
    public void delete(Integer id) {
        typeDAO.delete(id);
    }
}
