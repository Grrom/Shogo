package com.example.shogo.helpers;

import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Field;

public class CustomTimePicker extends TimePickerDialog {
    private int mMinHour = -1;
    private int mMinMinute = -1;
    private int mCurrentHour;
    private int mCurrentMinute;
    private Context mContext;

    public CustomTimePicker(Context context, OnTimeSetListener callBack, int hourOfDay,
                            int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
        mCurrentHour = hourOfDay;
        mCurrentMinute = minute;
        mContext = context;
        // Somehow the onTimeChangedListener is not set by TimePickerDialog
        // in some Android Versions, so, Adding the listener using
        // reflections
        try {
            Class<?> superclass = getClass().getSuperclass();
            Field mTimePickerField = superclass.getDeclaredField("mTimePicker");
            mTimePickerField.setAccessible(true);
            TimePicker mTimePicker = (TimePicker) mTimePickerField.get(this);
            mTimePicker.setOnTimeChangedListener(this);
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public void setMin(int hour, int minute) {
        mMinHour = hour;
        mMinMinute = minute;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        super.onTimeChanged(view, hourOfDay, minute);
        boolean validTime;
        if (((hourOfDay < mMinHour) || (hourOfDay == mMinHour && minute < mMinMinute))) {
            Toast.makeText(mContext, "Invalid Time.", Toast.LENGTH_SHORT).show();
            validTime = false;
        } else {
            validTime = true;
        }
        if (validTime) {
            mCurrentHour = hourOfDay;
            mCurrentMinute = minute;
        } else {
            updateTime(mCurrentHour, mCurrentMinute);
        }
    }}