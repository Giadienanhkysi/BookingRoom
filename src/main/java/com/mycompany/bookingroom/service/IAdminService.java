package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.Admin;

public interface IAdminService {
    List<Admin> findAll();
    Admin findById(Integer id);
    Admin save(Admin admin);
    Admin update(Admin admin);    
    void delete(Integer id);
}
