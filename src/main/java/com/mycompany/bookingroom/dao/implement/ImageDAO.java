package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IImageDAO;
import com.mycompany.bookingroom.mapper.ImageMapper;
import com.mycompany.bookingroom.model.Image;

public class ImageDAO extends AbstractDAO<Image> implements IImageDAO {

    @Override
    public List<Image> findAll() {
        String sql = "SELECT * FROM dbo.Images";
        return query(sql, new ImageMapper());
    }

    @Override
    public Image findById(Integer id) {
        String sql = "SELECT * FROM dbo.Images WHERE id = ?";
        List<Image> images = query(sql, new ImageMapper(), id);
        return images.size() > 0 ? images.get(0) : null;
    }

    @Override
    public List<Image> findByHotelId(Integer id) {
        String sql = "SELECT * FROM dbo.Images WHERE Hotel_id = ?";
        List<Image> images = query(sql, new ImageMapper(), id);
        return images;
    }

    @Override
    public List<Image> findByRoomId(Integer id) {
        String sql = "SELECT * FROM dbo.Images WHERE Room_id = ?";
        List<Image> images = query(sql, new ImageMapper(), id);
        return images;
    }

    @Override
    public Integer save(Image Image) {
        String sql = "INSERT dbo.Images (ROOM_ID, HOTEL_ID, SLIDE_ID, LINK) VALUES(?, ?, ?, ?)";
        return insert(sql, Image.getRoom_id(), Image.getHotel_id(), Image.getSlide_id(), Image.getLink());
    }

    @Override
    public void update(Image Image) {
        String sql = "UPDATE dbo.Images SET Room_id = ?, Hotel_id = ?, Slide_id = ?, Link = ? WHERE id = ?";
        update(sql, Image.getRoom_id(), Image.getHotel_id(), Image.getSlide_id(), Image.getLink(), Image.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM dbo.Images WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<Image> findBySlideId(Integer id) {
        String sql = "SELECT * FROM dbo.Images WHERE Slide_ID = ?";
        List<Image> images = query(sql, new ImageMapper(), id);
        return images;
    }

    @Override
    public void deleteAllImageByForeignKey(Integer id, String option) {
        String sql = "DELETE FROM dbo.Images WHERE " + option + " = ?";
        update(sql, id);
    }
}
