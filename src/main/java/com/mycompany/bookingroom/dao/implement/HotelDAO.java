package com.mycompany.bookingroom.dao.implement;

import com.mycompany.bookingroom.dao.IHotelDAO;
import com.mycompany.bookingroom.mapper.HotelMapper;
import com.mycompany.bookingroom.model.Hotel;
import java.util.List;

public class HotelDAO extends AbstractDAO<Hotel> implements IHotelDAO{
        
    @Override
    public List<Hotel> findAll() {        
        String sql = "SELECT * FROM dbo.Hotels";
        return query(sql, new HotelMapper());
    }

    @Override
    public List<Hotel> findAllByLocation(String location) {
        location = "%" + location + "%";
        String sql = "SELECT * FROM dbo.Hotels WHERE address like ?";
        return query(sql, new HotelMapper(), location);
    }
    
    @Override
    public List<Hotel> findAllByNameOrLocation(String text){
        text = "%" + text + "%";
        String sql = "SELECT * FROM dbo.Hotels WHERE address like ? or name like ?";
        return query(sql, new HotelMapper(), text, text);
    }
    @Override
    public Hotel findById(Integer id) {         
        String sql = "SELECT * FROM dbo.Hotels WHERE id = ?";        
        List<Hotel> hotels = query(sql, new HotelMapper(), id);
        return hotels.size() > 0 ? hotels.get(0) : null;
    }    

    @Override
    public Integer save(Hotel hotel) {        
        String sql = "INSERT dbo.Hotels(NAME,ADDRESS, PHONE, DESCRIPTION,STAR)VALUES(?, ?, ?, ?, ?)";
        return insert(sql, hotel.getName(), hotel.getAddress(), hotel.getPhone(), hotel.getDescription(), hotel.getStar());
    }

    @Override
    public void update(Hotel hotel) {
        String sql = "UPDATE dbo.Hotels SET Name = ? , Address = ?, Phone = ?, Description = ?, Star = ? "
                + " WHERE id = ?";        
        update(sql, hotel.getName(), hotel.getAddress(), hotel.getPhone(), hotel.getDescription(), hotel.getStar(), hotel.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Hotels WHERE id = ?";
        update(sql, id);
    }

    
    
}
