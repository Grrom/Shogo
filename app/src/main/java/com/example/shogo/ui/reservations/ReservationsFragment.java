package com.example.shogo.ui.reservations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        setupReservations();

        ReservationsAdapter adapter = new ReservationsAdapter(getContext(), reservations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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