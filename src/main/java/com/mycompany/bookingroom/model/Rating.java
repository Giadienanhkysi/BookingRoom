package com.mycompany.bookingroom.model;

import java.sql.Timestamp;

public class Rating {
	private int id, user_id, room_id, star;
	private String content;
	private Timestamp created_at, updated_at;
	
	public Rating() {

	}

	public Rating(int id, int user_id, int room_id, int star, String content, Timestamp created_at,
			Timestamp updated_at) {
		this.id = id;
		this.user_id = user_id;
		this.room_id = room_id;
		this.star = star;
		this.content = content;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Rating(int id, int user_id, int room_id, int star, String content) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.room_id = room_id;
		this.star = star;
		this.content = content;
	}

	public Rating(int user_id, int room_id, int star, String content) {
		super();
		this.user_id = user_id;
		this.room_id = room_id;
		this.star = star;
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getRoom_id() {
		return room_id;
	}

	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
}
