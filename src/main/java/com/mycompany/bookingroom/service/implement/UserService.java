package com.mycompany.bookingroom.service.implement;

import java.sql.Timestamp;
import java.util.List;

import com.mycompany.bookingroom.dao.IUserDAO;
import com.mycompany.bookingroom.model.User;
import com.mycompany.bookingroom.service.IUserService;
import javax.inject.Inject;

public class UserService implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id);
    }

    @Override
    public User save(User user) {
        int id = userDAO.save(user);
        return userDAO.findById(id);
    }

    @Override
    public User update(User user) {
        User oldUser = userDAO.findById(user.getId());
        user.setCreated_at(oldUser.getCreated_at());
        user.setLast_login(oldUser.getLast_login());
        user.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        userDAO.update(user);
        return userDAO.findById(user.getId());
    }

    @Override
    public void delete(Integer id) {
        userDAO.delete(id);
    }
}
