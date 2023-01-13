package com.example.shogo.models;

import java.sql.Time;
import java.util.Date;

public class ReservationModel {
    RoomModel room;
    Date checkInTime;
    Date checkInDate;

    public ReservationModel(RoomModel room, Date checkInTime, Date checkInDate) {
        this.room = room;
        this.checkInTime = checkInTime;
        this.checkInDate = checkInDate;
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
