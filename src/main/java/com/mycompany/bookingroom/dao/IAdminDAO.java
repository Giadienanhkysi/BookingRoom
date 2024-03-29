package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.Admin;

public interface IAdminDAO {
    List<Admin> findAll();
    Admin findById(Integer id);
    Admin findByUsernameAndPassword(String username, String password);
    Integer save(Admin admin);
    void update(Admin admin);   
    void delete(Integer id);
}
