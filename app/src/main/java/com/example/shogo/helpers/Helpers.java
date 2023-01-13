package com.example.shogo.helpers;

import java.text.SimpleDateFormat;

abstract public class Helpers {
    public static SimpleDateFormat dateFormatter =  new SimpleDateFormat("MMM dd, yyyy");

    public static SimpleDateFormat timeFormatterWithDate = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
    public static SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a");
}
