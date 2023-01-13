package com.example.shogo.models;

import java.sql.Time;
import java.util.Date;

public class ReservationModel {
    String id;
    RoomModel room;
    Date checkInTime;
    Date checkInDate;

    public ReservationModel(String id,RoomModel room, Date checkInTime, Date checkInDate) {
        this.id = id;
        this.room = room;
        this.checkInTime = checkInTime;
        this.checkInDate = checkInDate;
    }

    public String getId() {
        return id;
    }

    public RoomModel getRoom() {
        return room;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }
}
