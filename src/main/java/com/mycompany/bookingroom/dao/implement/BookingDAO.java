package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IBookingDAO;
import com.mycompany.bookingroom.mapper.BookingDetailsMapper;
import com.mycompany.bookingroom.mapper.BookingMapper;
import com.mycompany.bookingroom.model.Booking;
import com.mycompany.bookingroom.model.BookingDetails;

public class BookingDAO extends AbstractDAO<Booking> implements IBookingDAO {

    @Override
    public List<Booking> findAll(Object ...params) {
        String sql = "SELECT * FROM Booking";
        for(Object ob: params){
            if(ob == null){
                return query(sql, new BookingMapper());                
            }
        }
        if(params[0].toString().equalsIgnoreCase("asc") || params[0].toString().equalsIgnoreCase("desc"))
            sql += " order by " + params[1].toString() + " " + params[0].toString();
        return query(sql, new BookingMapper());
    }
    
    
    @Override
    public List<Booking> findByUserId(Integer id, Object ...params) {
        String sql = "SELECT * FROM Booking WHERE User_ID = ? ";
        if(params[0].toString().equalsIgnoreCase("asc") || params[0].toString().equalsIgnoreCase("desc"))
            sql += " order by " + params[1].toString() + params[0].toString();
        return query(sql, new BookingMapper(), id);
    }
    
    @Override
    public Booking findById(Integer id) {
        String sql = "SELECT * FROM Booking WHERE id = ?";
        List<Booking> booking = query(sql, new BookingMapper(), id);
        return booking.size() > 0 ? booking.get(0) : null;
    }

    @Override
    public Integer save(Booking booking) {
        String sql = "INSERT Booking (USER_ID, ROOM_ID, CHECK_IN, CHECK_OUT, DISCOUNT_PERCENT, QTY, STATUS, CREATED_AT, UPDATED_AT) VALUES(?, ?, ?, ?, ?, ?, ?, getdate(), getdate()); ";
        return insert(sql, booking.getUser_id(), booking.getRoom_id(), booking.getCheck_in(),
                booking.getCheck_out(), booking.getDiscount_percent(), booking.getQty(), booking.getStatus());
    }

    @Override
    public void update(Booking booking) {
        String sql = "UPDATE Booking SET User_id = ? , Room_id = ?, Created_at = ?, Updated_at = ?, Check_in = ?, Check_out = ?, discount_percent = ?, qty = ?, status = ?"
                + " WHERE id = ?";
        update(sql, booking.getUser_id(), booking.getRoom_id(), booking.getCreated_at(), booking.getUpdated_at(),
                booking.getCheck_in(), booking.getCheck_out(), booking.getDiscount_percent(), booking.getQty(), booking.getStatus(), booking.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Booking WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<BookingDetails> findAllBookingDetails(Object... params) {
        String sql = "SELECT DISTINCT b.id AS ID, u.id AS UserId, r.id AS RoomId, h.id AS HotelId, " +
"u.FIRST_NAME AS FirstName, u.LAST_NAME AS LastName, u.Phone, h.name AS HotelName, " +
"t.name AS RoomType, (r.price - r.PRICE * r.DISCOUNT) AS RoomPrice, b.QTY AS Quantity, " +
"b.CHECK_IN, b.CHECK_OUT, b.DISCOUNT_PERCENT, b.CREATED_AT, b.UPDATED_AT, b.STATUS " +
"FROM dbo.Rooms r, dbo.Types t, dbo.Users u, dbo.Booking b, dbo.Hotels h " +
"WHERE b.USER_ID = u.id AND  r.ID = b.ROOM_ID AND r.TYPES_ID = t.ID AND r.HOTEL_ID = h.ID";
        for(Object ob: params){
            if(ob == null){
                return query(sql, new BookingDetailsMapper());                
            }
        }
        if(params[0].toString().equalsIgnoreCase("asc") || params[0].toString().equalsIgnoreCase("desc"))
            sql += " order by " + params[1].toString() + " " + params[0].toString();
        return query(sql, new BookingDetailsMapper());
    }

    
}
