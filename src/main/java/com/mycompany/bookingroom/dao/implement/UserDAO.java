package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IUserDAO;
import com.mycompany.bookingroom.mapper.UserMapper;
import com.mycompany.bookingroom.model.User;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM Users";
        return query(sql, new UserMapper());
    }

    @Override
    public User findById(Integer id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        List<User> users = query(sql, new UserMapper(), id);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Override
    public Integer save(User user) {
        String sql = "INSERT Users(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, GENDER, "
                + "PHONE, EMAIL, ADDRESS, TOKEN, CREATED_AT, UPDATED_AT, LAST_LOGIN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, LOCALTIMESTAMP(), LOCALTIMESTAMP(), LOCALTIMESTAMP()); ";
        return insert(sql, user.getUsername(), user.getPassword(), user.getFirst_name(),
                user.getLast_name(), user.getGender(), user.getPhone(), user.getEmail(), user.getAddress(),
                user.getToken());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE Users SET Username = ? , Password = ?, First_name = ?, Last_name = ?, "
                + "Gender = ?, Phone = ?, Email = ?, Address = ?, Token = ?, Created_at = ?, Updated_at = ?, Last_login = ?"
                + " WHERE id = ?";
        update(sql, user.getUsername(), user.getPassword(), user.getFirst_name(),
                user.getLast_name(), user.getGender(), user.getPhone(), user.getEmail(), user.getAddress(),
                user.getToken(), user.getCreated_at(), user.getUpdated_at(), user.getLast_login(), user.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Users WHERE id = ?";
        update(sql, id);
    }
}
