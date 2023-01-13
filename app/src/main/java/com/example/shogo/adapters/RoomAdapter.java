package com.example.shogo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.RoomActivity;
import com.example.shogo.models.RoomModel;

import java.util.ArrayList;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {
    Context context;
    ArrayList<RoomModel> rooms;

    public RoomAdapter(Context context, ArrayList<RoomModel> rooms) {
        this.context = context;
        this.rooms = rooms;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.room_item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(rooms.get(position).getName());
        holder.price.setText(String.valueOf(rooms.get(position).getPrice()));
        holder.image.setImageResource(rooms.get(position).getImage());
        holder.viewButton.setOnClickListener(view -> {
            Intent intent = new Intent(context, RoomActivity.class);
            intent.putExtra("theRoom", rooms.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        TextView price;
        Button viewButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.room_image);
            name = itemView.findViewById(R.id.room_name);
            price = itemView.findViewById(R.id.room_price);
            viewButton = itemView.findViewById(R.id.view_button);
        }
    }
}
