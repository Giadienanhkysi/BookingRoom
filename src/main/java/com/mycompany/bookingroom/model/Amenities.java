package com.mycompany.bookingroom.model;

public class Amenities {
	private int id;
	private String amenities;
	
	public Amenities(int id, String amenities) {;
		this.id = id;
		this.amenities = amenities;
	}

	public Amenities() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}
}
