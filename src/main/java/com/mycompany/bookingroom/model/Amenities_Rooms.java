package com.mycompany.bookingroom.model;

import java.sql.Timestamp;

public class Amenities_Rooms {
    private Integer Room_id;
    private Integer Amenities_id;
    private Timestamp created_at;


    public Amenities_Rooms() {
    }


    public Amenities_Rooms(Integer Room_id, Integer Amenities_id, Timestamp created_at) {
        this.Room_id = Room_id;
        this.Amenities_id = Amenities_id;
        this.created_at = created_at;
    }
    
    
    public Integer getRoom_id() {
        return Room_id;
    }


    public void setRoom_id(Integer Room_id) {
        this.Room_id = Room_id;
    }


    public Integer getAmenities_id() {
        return Amenities_id;
    }


    public void setAmenities_id(Integer Amenities_id) {
        this.Amenities_id = Amenities_id;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
