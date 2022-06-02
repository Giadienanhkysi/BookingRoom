package com.mycompany.bookingroom.dao.implement;

import java.util.List;
import com.mycompany.bookingroom.dao.IAmenitiesDAO;
import com.mycompany.bookingroom.mapper.AmenitiesMapper;
import com.mycompany.bookingroom.model.Amenities;

public class AmenitiesDAO extends AbstractDAO<Amenities> implements IAmenitiesDAO {

    @Override
    public List<Amenities> findAll() {
        String sql = "SELECT * FROM dbo.Amenities";
        return query(sql, new AmenitiesMapper());
    }

    @Override
    public Amenities findById(Integer id) {
        String sql = "SELECT * FROM dbo.Amenities WHERE id = ?";
        List<Amenities> amenities = query(sql, new AmenitiesMapper(), id);
        return amenities.size() > 0 ? amenities.get(0) : null;
    }

    @Override
    public Integer save(Amenities amenities) {
        String sql = "INSERT dbo.Amenities (AMENITIES) VALUES(?)";
        return insert(sql, amenities.getAmenities());
    }

    @Override
    public void update(Amenities amenities) {
        String sql = "UPDATE dbo.Amenities SET Amenities = ? WHERE id = ?";
        update(sql, amenities.getAmenities(), amenities.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Amenities WHERE id = ?";
        update(sql, id);
    }
}
