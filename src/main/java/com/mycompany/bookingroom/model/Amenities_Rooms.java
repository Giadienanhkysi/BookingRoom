package com.mycompany.bookingroom.model;

import java.sql.Timestamp;

public class Amenities_Rooms {
    private Integer room_id;
    private Integer amenities_id;
    private Timestamp created_at;


    public Amenities_Rooms() {
    }


    public Amenities_Rooms(Integer room_id, Integer amenities_id, Timestamp created_at) {
        this.room_id = room_id;
        this.amenities_id = amenities_id;
        this.created_at = created_at;
    }
    
    
    public Integer getRoom_id() {
        return room_id;
    }


    public void setRoom_id(Integer room_id) {
        this.room_id = room_id;
    }


    public Integer getAmenities_id() {
        return amenities_id;
    }


    public void setAmenities_id(Integer amenities_id) {
        this.amenities_id = amenities_id;
    }


    public Timestamp getCreated_at() {
        return created_at;
    }


    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
