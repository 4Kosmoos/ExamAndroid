package edu.esiea.examandroid.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.esiea.examandroid.R;

public class MapFragment extends Fragment {

    private Button buttonGoToList;

    public MapFragment() {
        // Required empty public constructor
    }
@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_map, container, false);
    buttonGoToList = view.findViewById(R.id.button_go_to_list);

    buttonGoToList.setOnClickListener(v -> {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.listFragment);
    });

    return view;
}
}