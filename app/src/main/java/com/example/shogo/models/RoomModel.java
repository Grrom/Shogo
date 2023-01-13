package com.example.shogo.models;

import java.io.Serializable;

public class RoomModel implements Serializable {
    int id;
    RoomType roomType;
    int image;
    String name;
    double price;

    public RoomModel(int id,RoomType roomType, int image, String name, double price) {
        this.id = id;
        this.roomType=roomType;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getId() {
        return id;
    }


    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
