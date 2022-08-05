package com.mycompany.bookingroom.service.implement;

import java.sql.Timestamp;
import java.util.List;

import com.mycompany.bookingroom.dao.ISlideDAO;
import com.mycompany.bookingroom.model.Slide;
import com.mycompany.bookingroom.model.SlideImage;
import com.mycompany.bookingroom.service.ISlideService;
import javax.inject.Inject;

public class SlideService implements ISlideService {

    @Inject
    private ISlideDAO slideDAO;

    @Override
    public List<Slide> findAll() {
        return slideDAO.findAll();
    }

    @Override
    public Slide findById(Integer id) {
        return slideDAO.findById(id);
    }

    @Override
    public Slide save(Slide slide) {
        Integer id = slideDAO.save(slide);
        return slideDAO.findById(id);
    }

    @Override
    public Slide update(Slide slide) {
        Slide oldSlide = findById(slide.getId());
        slide.setCreated_at(oldSlide.getCreated_at());
        slide.setUpdated_at(new Timestamp(System.currentTimeMillis()));
        slideDAO.update(slide);
        return slideDAO.findById(slide.getId());
    }

    @Override
    public void delete(Integer id) {
        slideDAO.delete(id);
    }

    @Override
    public List<SlideImage> findSlideImageByGroup(Integer grp) {
         return slideDAO.findSlideImageByGroup(grp);
    }
}
