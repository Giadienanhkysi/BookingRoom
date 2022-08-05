package com.mycompany.bookingroom.dao.implement;

import com.mycompany.bookingroom.dao.IAmenities_RoomsDAO;
import com.mycompany.bookingroom.mapper.AmenitiesMapper;
import com.mycompany.bookingroom.mapper.Amenities_RoomsMapper;
import com.mycompany.bookingroom.model.Amenities;
import com.mycompany.bookingroom.model.Amenities_Rooms;
import java.util.List;


public class Amenities_RoomsDAO extends AbstractDAO<Amenities_Rooms> implements IAmenities_RoomsDAO{

    @Override
    public List<Amenities_Rooms> findAll() {
        String sql = "SELECT * FROM dbo.Amenities_Rooms";
        return query(sql, new Amenities_RoomsMapper());
    }

    @Override
    public Amenities_Rooms findById(Integer id) {
        String sql = "SELECT * FROM dbo.Amenities_Rooms WHERE id = ?";
        List<Amenities_Rooms> amenities_rooms = query(sql, new Amenities_RoomsMapper(), id);
        return amenities_rooms.size() > 0 ? amenities_rooms.get(0) : null;
    }

    @Override
    public List<Amenities_Rooms> findByRoomId(Integer id) {
        String sql = "SELECT * FROM dbo.Amenities_Rooms where room_id = ?";
        return query(sql, new Amenities_RoomsMapper(), id);
    }
    
    @Override
    public List<Amenities> findAmenitiesByRoomId(Integer id) {
        String sql = "SELECT ID, AMENITIES FROM dbo.Amenities_Rooms JOIN  dbo.Amenities ON Amenities.ID = Amenities_Rooms.Amenities_ID WHERE Room_ID = ?";
        return query(sql, new AmenitiesMapper(), id);
    }
    

    @Override
    public Integer save(Amenities_Rooms ar) {
        String sql = "INSERT dbo.Amenities_Rooms(Room_ID, Amenities_ID, Created_at)VALUES(?, ?, GETDATE() )";
        return insert(sql,ar.getRoom_id(), ar.getAmenities_id());
    }

    @Override
    public void update(Amenities_Rooms ar) {
        String sql = "UPDATE dbo.Amenities_rooms SET Room_ID =?, Amenities_ID=?, Created_at=Getdate()";
        update(sql, ar.getRoom_id(), ar.getAmenities_id());
    }
    
    
    @Override
    public void delete(Amenities_Rooms ar) {
        String sql = "DELETE FROM dbo.Amenities_Rooms WHERE Room_ID = ? AND Amenities_ID = ?";
        update(sql, ar.getRoom_id(), ar.getAmenities_id());
    }

    @Override
    public void deleteByRoomId(Integer roomId) {
        String sql = "DELETE FROM dbo.Amenities_Rooms WHERE Room_ID = ?";
        update(sql, roomId);
    }

}
