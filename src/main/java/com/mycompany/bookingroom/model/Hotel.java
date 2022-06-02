package com.mycompany.bookingroom.model;


public class Hotel extends AbstractModel<Hotel>{    
    private String name;
    private String address;
    private String description;
//    private String thumnail;
//    private Double price;
    private int star;
    
    public Hotel() {
    }

    public Hotel(Integer id, String name, String address, String description, int star) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
//        this.thumnail = thumnail;
//        this.price = price;
        this.star = star;
    }

    public Hotel(String name, String address, String description, int star) {
        this.name = name;
        this.address = address;
        this.description = description;
//        this.thumnail = thumnail;
//        this.price = price;
        this.star = star;
    }
    
    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getThumnail() {
//        return thumnail;
//    }
//
//    public void setThumnail(String thumnail) {
//        this.thumnail = thumnail;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
    
    
    
}
