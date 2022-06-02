package com.mycompany.bookingroom.service.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IAdminDAO;
import com.mycompany.bookingroom.dao.implement.AdminDAO;
import com.mycompany.bookingroom.model.Admin;
import com.mycompany.bookingroom.service.IAdminService;
import java.sql.Timestamp;
import javax.inject.Inject;

public class AdminService implements IAdminService{
	
    @Inject
    private IAdminDAO adminDAO;        

    
    @Override
    public List<Admin> findAll() {
        return adminDAO.findAll();
    }

    @Override
    public Admin findById(Integer id) {
        return adminDAO.findById(id);
    }

    @Override
    public Admin save(Admin newAdmin) {
        Integer id = adminDAO.save(newAdmin);                          
        return adminDAO.findById(id);
    }

    @Override
    public Admin update(Admin newAdmin) {
        Admin oldAdmin = findById(newAdmin.getId());        
        newAdmin.setCreated_at(oldAdmin.getCreated_at());
        newAdmin.setLast_login(oldAdmin.getLast_login());
        newAdmin.setUpdated_at(new Timestamp(System.currentTimeMillis()));        
        adminDAO.update(newAdmin);
        return adminDAO.findById(newAdmin.getId());
    }

    @Override
    public void delete(Integer id) {
        adminDAO.delete(id);
    }
}
