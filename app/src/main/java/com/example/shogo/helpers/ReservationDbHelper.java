package com.example.shogo.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.shogo.models.ReservationModel;

import java.text.ParseException;
import java.util.ArrayList;

public class ReservationDbHelper extends SQLiteOpenHelper {

    private static final String TAG ="ReservationDbHelper" ;

    static final class AppointmentContract {
        private AppointmentContract() {}

        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "reservations";
            public static final String ROOM_ID = "room_id";
            public static final String DATE = "date";
            public static final String TIME = "time";
        }
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "reservations.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + AppointmentContract.FeedEntry.TABLE_NAME + " (" +
                    AppointmentContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    AppointmentContract.FeedEntry.ROOM_ID + " TEXT," +
                    AppointmentContract.FeedEntry.DATE + " TEXT," +
                    AppointmentContract.FeedEntry.TIME + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AppointmentContract.FeedEntry.TABLE_NAME;

    public ReservationDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static boolean createReservation(Context context, ReservationModel reservation){

        ReservationDbHelper dbHelper = new ReservationDbHelper(context);

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Log.e(TAG, "createReservation: "+ reservation.getCheckInTime() );

        ContentValues values = new ContentValues();
        values.put(AppointmentContract.FeedEntry.ROOM_ID, reservation.getRoom().getId());
        values.put(AppointmentContract.FeedEntry.DATE, Helpers.dateFormatter.format(reservation.getCheckInDate()));
        values.put(AppointmentContract.FeedEntry.TIME, Helpers.timeFormatterWithDate.format(reservation.getCheckInTime()));


        return db.insert(AppointmentContract.FeedEntry.TABLE_NAME, null, values) >= 0;
    }

    public static ArrayList<ReservationModel> getReservations(Context context)  {
        ReservationDbHelper dbHelper = new ReservationDbHelper(context);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                BaseColumns._ID,
                AppointmentContract.FeedEntry.ROOM_ID,
                AppointmentContract.FeedEntry.DATE,
                AppointmentContract.FeedEntry.TIME,
        };


        Cursor cursor = db.query(
                AppointmentContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );


        ArrayList<ReservationModel> reservations = new ArrayList<>();

        while(cursor.moveToNext()) {
            String _id= cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry._ID));
            int _room_id= cursor.getInt(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.ROOM_ID));
            String _date= cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.DATE));
            String _time= cursor.getString(cursor.getColumnIndexOrThrow(AppointmentContract.FeedEntry.TIME));

            try {
                Log.e(TAG,  _time );
                reservations.add(new ReservationModel(_id,ShogoConstants.getRoomById(_room_id),Helpers.timeFormatterWithDate.parse(_time),Helpers.dateFormatter.parse(_date)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cursor.close();

        return reservations;
    }


    public static void deleteReservation(Context context,String reservationId){
        ReservationDbHelper dbHelper = new ReservationDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String selection = AppointmentContract.FeedEntry._ID + " LIKE ?";
        String[] selectionArgs = { reservationId };
        db.delete(AppointmentContract.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }
}