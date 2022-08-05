package com.mycompany.bookingroom.service;

import java.util.List;

import com.mycompany.bookingroom.model.User;

public interface IUserService {

    List<User> findAll();

    User findById(Integer id);

    User save(User user);

    User findByUsernameAndPassword(String username, String password);

    User update(User user);

    void delete(Integer id);
}
