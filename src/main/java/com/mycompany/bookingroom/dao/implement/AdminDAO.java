package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IAdminDAO;
import com.mycompany.bookingroom.mapper.AdminMapper;
import com.mycompany.bookingroom.model.Admin;

public class AdminDAO extends AbstractDAO<Admin> implements IAdminDAO{	
        
    @Override
    public List<Admin> findAll() {        
        String sql = "SELECT * FROM dbo.Admins";
        return query(sql, new AdminMapper());
    }	

    @Override
    public Admin findById(Integer id) {         
        String sql = "SELECT * FROM dbo.Admins WHERE id = ?";        
        List<Admin> admins = query(sql, new AdminMapper(), id);
        return admins.size() > 0 ? admins.get(0) : null;
    }
    
    @Override
    public Integer save(Admin admin) {
        String sql = "INSERT dbo.Admins(USERNAME, PASSWORD, FIRST_NAME, LAST_NAME, "
                    + "PHONE, EMAIL, ADDRESS, TOKEN, CREATED_AT, UPDATED_AT, LAST_LOGIN) VALUES(?, ?, ?, ?, ?, ?, ?, ?, getdate(), getdate(), getdate())";            
        return insert(sql, admin.getUsername(), admin.getPassword(), admin.getFirst_name(),
                    admin.getLast_name(), admin.getPhone(), admin.getEmail(), admin.getAddress(),
                    admin.getToken());
    }

    @Override
    public void update(Admin admin) {
        String sql = "UPDATE dbo.Admins SET Username = ? , Password = ?, First_name = ?, Last_name = ?, "
                        + "Phone = ?, Email = ?, Address = ?, Token = ?, Created_at = ?, Updated_at = ?, Last_login = ?"
                + " WHERE id = ?";        
        update(sql, admin.getUsername(), admin.getPassword(), admin.getFirst_name(),
                        admin.getLast_name(), admin.getPhone(), admin.getEmail(), admin.getAddress(),
                        admin.getToken(), admin.getCreated_at(), admin.getUpdated_at(), admin.getLast_login(), admin.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Admins WHERE id = ?";
        update(sql, id);
    }   
}
