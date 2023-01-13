package com.example.shogo.ui.rooms;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.adapters.RoomAdapter;
import com.example.shogo.databinding.FragmentRoomsBinding;
import com.example.shogo.helpers.ReservationDbHelper;
import com.example.shogo.helpers.ShogoConstants;
import com.example.shogo.models.ReservationModel;
import com.example.shogo.models.RoomModel;
import com.example.shogo.models.RoomType;

import java.util.ArrayList;

public class RoomsFragment extends Fragment {

    private FragmentRoomsBinding binding;
    ArrayList<RoomModel> rooms = new ArrayList<>();
    RoomAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRoomsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.roomsRecyclerView;
        EditText searchRoom = binding.searchRoom;
        Spinner range = binding.priceRange;

        setupRooms();

        adapter = new RoomAdapter(getContext(), rooms);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchRoom.setText("");
                String selected;
                if(view==null){
                    selected= "all";
                }else{
                    selected= ((TextView)view).getText().toString();
                }

                setupRooms();

                if(!selected.equals("all")){
                    if(selected.equals("less than 100 php")){
                        rooms.removeIf(s -> s.getPrice()>100);
                    }else if(selected.equals("100 - 300 php")){
                        rooms.removeIf(s -> s.getPrice()<100 || s.getPrice()>300);
                    }else if(selected.equals("more than 300 php")){
                        rooms.removeIf(s -> s.getPrice()<300);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchRoom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable chars) {
                String input = chars.toString();

                if(!input.isEmpty()){
                    range.setSelection(0);
                }

                setupRooms();
                rooms.removeIf(s -> !(s.getName()).toLowerCase().contains((input.toLowerCase())));

                adapter.notifyDataSetChanged();
            }
        } );

        return root;
    }

    public void setupRooms() {
        rooms.clear();
        rooms.addAll(ShogoConstants.getRooms());
        rooms.removeIf(rr->ReservationDbHelper.getReservedRoomsIds(getContext()).contains(rr.getId()));

        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        setupRooms();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}