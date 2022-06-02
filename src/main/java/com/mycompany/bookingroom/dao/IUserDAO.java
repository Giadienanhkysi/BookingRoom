package com.mycompany.bookingroom.dao;

import java.util.List;

import com.mycompany.bookingroom.model.User;

public interface IUserDAO {
	List<User> findAll();
    User findById(Integer id);
    Integer save(User user);
    void update(User user);   
    void delete(Integer id);
}
