package com.example.shogo.ui.reservations;

import android.media.audiofx.PresetReverb;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shogo.R;
import com.example.shogo.adapters.ReservationsAdapter;
import com.example.shogo.databinding.FragmentReservationsBinding;
import com.example.shogo.helpers.ReservationDbHelper;
import com.example.shogo.models.ReservationModel;
import com.example.shogo.models.RoomModel;
import com.example.shogo.models.RoomType;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;


public class ReservationsFragment extends Fragment {

    private FragmentReservationsBinding binding;
    ArrayList<ReservationModel> reservations = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.reservationsRecyclerView;
        EditText searchReservation = binding.searchReservation;
        Spinner range = binding.priceRange;

        setupReservations();

        ReservationsAdapter adapter = new ReservationsAdapter(getContext(), reservations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        range.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchReservation.setText("");
                String selected;
                if(view==null){
                    selected= "all";
                }else{
                    selected= ((TextView)view).getText().toString();
                }

                setupReservations();

                if(!selected.equals("all")){
                    if(selected.equals("less than 100 php")){
                        reservations.removeIf(s -> s.getRoom().getPrice()>100);
                    }else if(selected.equals("100 - 300 php")){
                        reservations.removeIf(s -> (s.getRoom().getPrice()<100 || s.getRoom().getPrice()>300));
                    }else if(selected.equals("more than 300 php")){
                        reservations.removeIf(s -> s.getRoom().getPrice()<300);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        searchReservation.addTextChangedListener(new TextWatcher() {
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

                setupReservations();
                reservations.removeIf(s -> !(s.getRoom().getName()).toLowerCase().contains((input.toLowerCase())));

                adapter.notifyDataSetChanged();
            }
        } );
        return root;
    }

    public void setupReservations() {
        reservations.clear();
        reservations.addAll(ReservationDbHelper.getReservations(getContext()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}