package com.example.shogo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.models.ReservationModel;

import java.util.ArrayList;


public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.MyViewHolder> {
    Context context;
    ArrayList<ReservationModel> reservations;

    public ReservationsAdapter(Context context, ArrayList<ReservationModel> reservations) {
        this.context = context;
        this.reservations = reservations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.reservation_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(reservations.get(position).getRoom().getName());
        holder.price.setText(String.valueOf(reservations.get(position).getRoom().getPrice()));
        holder.image.setImageResource(reservations.get(position).getRoom().getImage());
        holder.date.setText(reservations.get(position).getCheckInDate().toString());
        holder.time.setText(reservations.get(position).getCheckInTime().toString());
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView price;
        TextView date;
        TextView time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.room_image);
            name = itemView.findViewById(R.id.room_name);
            price = itemView.findViewById(R.id.room_price);
            date = itemView.findViewById(R.id.reservation_date);
            time = itemView.findViewById(R.id.reservation_time);
        }
    }
}
