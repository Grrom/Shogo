package com.example.shogo.helpers;

import com.example.shogo.R;
import com.example.shogo.models.RoomModel;
import com.example.shogo.models.RoomType;

import java.sql.Array;
import java.util.ArrayList;

public class ShogoConstants {

    public static RoomModel getRoomById(int id){
        ArrayList<RoomModel>  theRooms = new ArrayList<>();
        theRooms.addAll(getRooms());
        theRooms.removeIf(room->room.getId()!=id);

        return  theRooms.get(0);
    }


    public static ArrayList<RoomModel> getRooms(){
        ArrayList rooms = new ArrayList();

        int[] roomsImgClassic =  {R.drawable.classic_1, R.drawable.classic_2, R.drawable.classic_3};
        int[] roomsImgDeluxe =  { R.drawable.deluxe_1, R.drawable.deluxe_2,R.drawable.deluxe_3};

        for (int i = 1; i <= roomsImgClassic.length; i++) {
            rooms.add(new RoomModel(i, RoomType.classic,roomsImgClassic[i-1], "Classic room " + i, 100.00 + (i*10)));
        }

        for (int i = 1; i <= roomsImgDeluxe.length; i++) {
            rooms.add(new RoomModel(i+roomsImgClassic.length,RoomType.deluxe,roomsImgDeluxe[i-1], "Deluxe room " + i, 300.00 + (i*20)));
        }

        return rooms;
    }

}
