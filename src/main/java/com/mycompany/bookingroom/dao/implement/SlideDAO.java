package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.ISlideDAO;
import com.mycompany.bookingroom.mapper.SlideImageMapper;
import com.mycompany.bookingroom.mapper.SlideMapper;
import com.mycompany.bookingroom.model.Slide;
import com.mycompany.bookingroom.model.SlideImage;

public class SlideDAO extends AbstractDAO<Slide> implements ISlideDAO {

    @Override
    public List<Slide> findAll() {
        String sql = "SELECT * FROM Slides";
        return query(sql, new SlideMapper());
    }

    @Override
    public Slide findById(Integer id) {
        String sql = "SELECT * FROM Slides WHERE id = ?";
        List<Slide> slides = query(sql, new SlideMapper(), id);
        return slides.size() > 0 ? slides.get(0) : null;
    }

    @Override
    public Integer save(Slide slide) {
        String sql = "INSERT Slides(LINK, TITLE, DESCRIPTION, GRP, CREATED_AT, UPDATED_AT) VALUES(?, ?, ?, ?, getdate(), getdate()); ";
        return insert(sql, slide.getLink(), slide.getTitle(), slide.getDescription(), slide.getGroup());
    }

    @Override
    public void update(Slide slide) {
        String sql = "UPDATE Slides SET Link = ? , TITLE = ?, DESCRIPTION = ?, GRP = ?,Created_at = ?, Updated_at = ? WHERE id = ?";
        update(sql, slide.getLink(), slide.getTitle(), slide.getDescription(), slide.getGroup(),
                slide.getCreated_at(), slide.getUpdated_at(), slide.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Slides WHERE id = ?";
        update(sql, id);
    }

    @Override
    public List<SlideImage> findSlideImageByGroup(Integer grp) {
        String sql = "SELECT s.ID AS ID, s.LINK AS slideLink, s.CREATED_AT, s.UPDATED_AT, s.TITLE, " +
                    "s.DESCRIPTION, s.grp, i.ID AS imageId, i.Room_ID, i.Hotel_ID, i.Slide_ID, i.LINK AS imageLink " +
                    "FROM dbo.Slides s INNER JOIN dbo.Images i ON i.Slide_ID = s.ID where grp=?";
        return query(sql, new SlideImageMapper(), grp);
    }
    
}
