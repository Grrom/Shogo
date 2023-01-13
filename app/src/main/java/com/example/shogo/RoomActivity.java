package com.example.shogo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ThemedSpinnerAdapter;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.shogo.helpers.CustomTimePicker;
import com.example.shogo.helpers.Helpers;
import com.example.shogo.models.ReservationModel;
import com.example.shogo.models.RoomModel;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RoomActivity extends AppCompatActivity {

    private Calendar today = Calendar.getInstance();
    Date selectedDate;
    Date selectedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        RoomModel room = (RoomModel)getIntent().getSerializableExtra("theRoom");

        getSupportActionBar().setTitle("Reserve a room");

        TextView name = findViewById(R.id.room_name);
        TextView price = findViewById(R.id.room_price);
        ImageView image = findViewById(R.id.room_image);

        Button date = findViewById(R.id.date_input);
        Button time = findViewById(R.id.time_input);

        time.setEnabled(false);

        name.setText(room.getName());
        price.setText(room.getPrice() + " php");
        image.setImageResource(room.getImage());

        Button reserveButton = findViewById(R.id.reserve_button);



        date.setOnClickListener(view->{
            DatePickerDialog dp = new DatePickerDialog(this, (view1, year, month, dayOfMonth) ->{
                selectedDate = new Date(year-1900, month, dayOfMonth);
                date.setText(Helpers.dateFormatter.format(selectedDate));
                time.setEnabled(true);
            } ,today.get(Calendar.YEAR) , today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));


            dp.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dp.show();
        });

        time.setOnClickListener(view->{
            CustomTimePicker tp = new CustomTimePicker(this, (view12, hourOfDay, minute) -> {
                    selectedTime = new Date(selectedDate.getYear(), selectedDate.getMonth(),selectedDate.getDay(), hourOfDay, minute);
                    time.setText(Helpers.timeFormatter.format(selectedTime));
            },today.getTime().getHours(), today.getTime().getMinutes(), false);

            if(date.getText().toString().equals(Helpers.dateFormatter.format(today.getTime()))){
                tp.setMin(today.getTime().getHours(), today.getTime().getMinutes());
            }

            tp.show();
        });


        reserveButton.setOnClickListener(view -> {
            if(selectedDate==null || selectedTime==null){
                Toast.makeText(this, "Please fill all the fields.", Toast.LENGTH_LONG).show();
                return;
            }

            return;

//            try {
//                ReservationModel reservation = new ReservationModel(room , Helpers.timeFormatter.parse(String.valueOf(time.getText())), Helpers.dateFormatter.parse(date.getText().toString()) );
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            startActivity(new Intent(this, CheckoutActivity.class));
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}