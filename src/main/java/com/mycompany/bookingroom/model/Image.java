package com.mycompany.bookingroom.model;

public class Image {
    private Integer id, room_id, hotel_id;
    private String link;

    public Image() {

    }

    public Image(Integer id, Integer room_id, Integer hotel_id, String link) {
        this.id = id;
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Integer room_id) {   
        this.room_id = room_id;
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
