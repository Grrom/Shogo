package com.example.shogo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.helpers.Helpers;
import com.example.shogo.helpers.ReservationDbHelper;
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
       holder.id = reservations.get(position).getId();

        holder.name.setText(reservations.get(position).getRoom().getName());
        holder.price.setText(reservations.get(position).getRoom().getPrice()+" php");
        holder.image.setImageResource(reservations.get(position).getRoom().getImage());
        holder.date.setText(Helpers.dateFormatter.format(reservations.get(position).getCheckInDate()));
        holder.time.setText(Helpers.timeFormatter.format(reservations.get(position).getCheckInTime()));

        holder.cancel.setOnClickListener(view->{
            ReservationDbHelper.deleteReservation(context, holder.id);
            reservations.removeIf(re->re.getId()==holder.id);
            this.notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        String id ;

        ImageView image;
        TextView name;
        TextView price;
        TextView date;
        TextView time;
        Button cancel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.room_image);
            name = itemView.findViewById(R.id.room_name);
            price = itemView.findViewById(R.id.room_price);
            date = itemView.findViewById(R.id.reservation_date);
            time = itemView.findViewById(R.id.reservation_time);
            cancel = itemView.findViewById(R.id.cancel_reservation);
            id="";
        }
    }
}
