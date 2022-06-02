package com.mycompany.bookingroom.model;

import java.sql.Timestamp;

public class Room extends AbstractModel<Room>{
    private Integer hotel_id;
    private Integer types_id;
    private String name;
    private double Area;
    private String description;
    private double price;
    private Integer person_capacity;
    private String token;
    private double discount;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Integer qty;

    public Room(Integer hotel_id, Integer types_id, String name, double Area, String description, double price, Integer person_capacity, String token, double discount, Timestamp created_at, Timestamp updated_at, Integer qty) {
        super();
        this.hotel_id = hotel_id;
        this.types_id = types_id;
        this.name = name;
        this.Area = Area;
        this.description = description;
        this.price = price;
        this.person_capacity = person_capacity;
        this.token = token;
        this.discount = discount;
        this.qty = qty;
        this.created_at = created_at;
        this.updated_at = updated_at;        
    }
    
    

    public Room() {
    }

    public Integer getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Integer hotel_id) {
        this.hotel_id = hotel_id;
    }

    public Integer getTypes_id() {
        return types_id;
    }

    public void setTypes_id(Integer types_id) {
        this.types_id = types_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double Area) {
        this.Area = Area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getPerson_capacity() {
        return person_capacity;
    }

    public void setPerson_capacity(Integer person_capacity) {
        this.person_capacity = person_capacity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    
}
