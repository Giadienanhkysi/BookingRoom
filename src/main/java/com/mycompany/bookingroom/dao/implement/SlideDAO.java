package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.ISlideDAO;
import com.mycompany.bookingroom.mapper.SlideMapper;
import com.mycompany.bookingroom.model.Slide;

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
        String sql = "INSERT Slides(LINK, TITLE, DESCRIPTION, CREATED_AT, UPDATED_AT) VALUES(?, ?, ?, getdate(), getdate()); ";
        return insert(sql, slide.getLink(), slide.getTitle(), slide.getDescription());
    }

    @Override
    public void update(Slide slide) {
        String sql = "UPDATE Slides SET Link = ? , TITLE = ?, DESCRIPTION = ?, Created_at = ?, Updated_at = ? WHERE id = ?";
        update(sql, slide.getLink(), slide.getTitle(), slide.getDescription(),
                slide.getCreated_at(), slide.getUpdated_at(), slide.getId());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Slides WHERE id = ?";
        update(sql, id);
    }
}
