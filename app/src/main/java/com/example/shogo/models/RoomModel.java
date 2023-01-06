package com.example.shogo.models;

public class RoomModel {
    int image;
    String name;
    double price;

    public RoomModel(int image, String name, double price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getDescription() {
        return "So Clean So Good.";
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
