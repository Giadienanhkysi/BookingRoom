package com.mycompany.bookingroom.dao.implement;

import com.mycompany.bookingroom.dao.IRoomDAO;
import com.mycompany.bookingroom.mapper.RoomMapper;
import com.mycompany.bookingroom.model.Room;
import java.util.List;


public class RoomDAO extends AbstractDAO<Room> implements IRoomDAO{
    @Override
    public List<Room> findAll() {        
        String sql = "SELECT * FROM dbo.Rooms";
        return query(sql, new RoomMapper());
    }

    @Override
    public List<Room> findAllByHotel(int id) {        
        String sql = "SELECT * FROM dbo.Rooms WHERE Hotel_id = ?";
        return query(sql, new RoomMapper(), id);
    }
    
    @Override
    public List<Room> findAllByDateAndHotel(int id, String checkIn, String checkOut) {        
        String sql = "EXEC dbo.spValidRoom @check_in = ?, @check_out = ?, @hotel_id = ?";
        return query(sql, new RoomMapper(), checkIn, checkOut, id);
    }
    
    @Override
    public Room findById(Integer id) {         
        String sql = "SELECT * FROM dbo.Rooms WHERE id = ?";        
        List<Room> rooms = query(sql, new RoomMapper(), id);
        return rooms.size() > 0 ? rooms.get(0) : null;
    }    

    @Override
    public Integer save(Room room) {        
        String sql = "INSERT dbo.Rooms(hotel_id, Types_id, Name, Area, Description, "
                + "Price, Person_capacity, Token, qty, discount, Created_at, Updated_at)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, getdate(), getdate())";
        return insert(sql, room.getHotel_id(), room.getTypes_id(), room.getName(), room.getArea(),
                    room.getDescription(), room.getPrice(), room.getPerson_capacity(), room.getToken(), room.getQty(),
                    room.getDiscount());
    }

    @Override
    public void update(Room room) {
        String sql = "UPDATE dbo.Rooms SET hotel_id = ?, Types_id = ?, Name = ?, Area = ?, Description = ?, "
                + "Price = ?, Person_capacity = ?, Token = ?, Qty = ?, discount = ?, Created_at = ?, Updated_at = getdate() where id = ?";
        update(sql, room.getHotel_id(), room.getTypes_id(), room.getName(), room.getArea(),
                    room.getDescription(), room.getPrice(), room.getPerson_capacity(), room.getToken(), room.getQty(),
                    room.getDiscount(), room.getCreated_at(), room.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Rooms WHERE id = ?";
        update(sql, id);
    }
    
}
