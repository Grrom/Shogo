package com.example.shogo.ui.rooms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.adapters.RoomAdapter;
import com.example.shogo.databinding.FragmentRoomsBinding;
import com.example.shogo.helpers.ShogoConstants;
import com.example.shogo.models.RoomModel;
import com.example.shogo.models.RoomType;

import java.util.ArrayList;

public class RoomsFragment extends Fragment {

    private FragmentRoomsBinding binding;
    ArrayList<RoomModel> rooms = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRoomsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.roomsRecyclerView;

        setupRooms();

        RoomAdapter adapter = new RoomAdapter(getContext(), rooms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    public void setupRooms() {
        rooms.clear();
        rooms.addAll(ShogoConstants.getRooms());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}