package edu.esiea.examandroid.ui;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.R;
import edu.esiea.examandroid.adapter.PlaceAdapter;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.viewmodel.PlaceViewModel;

public class ListFragment extends Fragment {

    private Spinner spinnerFilter;
    private TextView emptyView;
    private RecyclerView recyclerView;
    private PlaceAdapter adapter;
    private PlaceViewModel placeViewModel;

    private List<PlaceWithDetails> allPlaces = new ArrayList<>();

    public ListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);

        spinnerFilter = root.findViewById(R.id.spinnerFilter);
        emptyView = root.findViewById(R.id.emptyView);
        recyclerView = root.findViewById(R.id.recycler_places);

        Button btnGoMap = root.findViewById(R.id.button_go_to_map);

        btnGoMap.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.mapFragment);
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(requireActivity())
                .get(PlaceViewModel.class);

        adapter = new PlaceAdapter(new PlaceAdapter.OnPlaceClickListener() {
            @Override
            public void onEditClicked(PlaceWithDetails pwd) {
                Bundle args = new Bundle();
                args.putInt("placeId", pwd.getPlace().getId());
                NavHostFragment.findNavController(ListFragment.this)
                        .navigate(R.id.action_list_to_detail, args);
            }

            @Override
            public void onDeleteClicked(PlaceWithDetails pwd) {
                new AlertDialog.Builder(requireContext())
                        .setTitle("Supprimer")
                        .setMessage("Confirmer la suppression ?")
                        .setNegativeButton("Annuler", (dialog, which) -> dialog.dismiss())
                        .setPositiveButton("Supprimer", (dialog, which) -> {
                            placeViewModel.removePlace(pwd.getPlace());
                            dialog.dismiss();
                        })
                        .show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        placeViewModel.getAllPlacesLive().observe(getViewLifecycleOwner(), list -> {
            allPlaces = list;
            updateList(list);
        });

        setupSpinner();
    }
    private void setupSpinner() {
        List<Object> items = new ArrayList<>();
        items.add("Tous");
        items.addAll(Arrays.asList(PlaceType.values()));

        ArrayAdapter<Object> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                items
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFilter.setAdapter(spinnerAdapter);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view,
                                       int position,
                                       long id) {
                Object selected = parent.getItemAtPosition(position);
                if (selected instanceof String && selected.equals("Tous")) {
                    updateList(allPlaces);
                } else if (selected instanceof PlaceType) {
                    filterPlaces((PlaceType) selected);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                updateList(allPlaces);
            }
        });
    }

    private void filterPlaces(PlaceType type) {
        List<PlaceWithDetails> filtered = new ArrayList<>();
        for (PlaceWithDetails pwd : allPlaces) {
            if (pwd.getPlace().getType() == type) {
                filtered.add(pwd);
            }
        }
        updateList(filtered);
    }

    private void updateList(List<PlaceWithDetails> newData) {
        if (newData == null || newData.isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.setData(newData);
        }
    }
}