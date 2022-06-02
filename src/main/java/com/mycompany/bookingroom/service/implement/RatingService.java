package com.mycompany.bookingroom.service.implement;

import java.sql.Timestamp;
import java.util.List;

import com.mycompany.bookingroom.dao.IRatingDAO;
import com.mycompany.bookingroom.model.Rating;
import com.mycompany.bookingroom.service.IRatingService;
import javax.inject.Inject;

public class RatingService implements IRatingService {

    @Inject
    private IRatingDAO ratingDAO;

    @Override
    public List<Rating> findAll() {
        return ratingDAO.findAll();
    }

    @Override
    public Rating findById(Integer id) {
        return ratingDAO.findById(id);
    }

    @Override
    public Rating save(Rating rating) {
        Integer id = ratingDAO.save(rating);
        return ratingDAO.findById(id);
    }

    @Override
    public Rating update(Rating rating) {
        Rating oldRating = findById(rating.getId());
        rating.setCreated_at(oldRating.getCreated_at());
        rating.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        ratingDAO.update(rating);
        return ratingDAO.findById(rating.getId());
    }

    @Override
    public void delete(Integer id) {
        ratingDAO.delete(id);
    }
}
