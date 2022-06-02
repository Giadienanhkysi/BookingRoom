package com.mycompany.bookingroom.dao.implement;

import java.util.List;

import com.mycompany.bookingroom.dao.IRatingDAO;
import com.mycompany.bookingroom.mapper.RatingMapper;
import com.mycompany.bookingroom.model.Rating;

public class RatingDAO extends AbstractDAO<Rating> implements IRatingDAO{

	@Override
	public List<Rating> findAll() {
		String sql = "SELECT * FROM Rating";
        return query(sql, new RatingMapper());
	}

	@Override
	public Rating findById(Integer id) {
		String sql = "SELECT * FROM Rating WHERE id = ?";        
        List<Rating> rating = query(sql, new RatingMapper(), id);
        return rating.size() > 0 ? rating.get(0) : null;
	}

	@Override
	public Integer save(Rating rating) {
		String sql = "INSERT Rating(USER_ID, ROOM_ID, CONTENT, STAR, CREATED_AT, UPDATED_AT) VALUES(?, ?, ?, ?, getdate(), getdate()); ";            
        return insert(sql, rating.getUser_id(), rating.getRoom_id(), rating.getContent(), rating.getStar());
	}

	@Override
	public void update(Rating rating) {
		String sql = "UPDATE Rating SET USER_ID = ?, ROOM_ID = ?, CREATED_AT = ?, UPDATED_AT = ?, CONTENT = ?, STAR = ? "
				+ "WHERE ID = ?"; 
        update(sql, rating.getUser_id(), rating.getRoom_id(), rating.getCreated_at(), rating.getUpdated_at(),
        		rating.getContent(), rating.getStar(), rating.getId());
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM Rating WHERE id = ?";
        update(sql, id);
	}

}
