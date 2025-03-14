package edu.esiea.examandroid.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.R;
import edu.esiea.examandroid.data.dao.ChildPlaceEntity;
import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.viewmodel.PlaceViewModel;


public class DetailFragment extends Fragment {

    private EditText editName, editDescription, editPhone, editEmail, editWebsite;
    private Spinner spinnerType;
    private Button buttonCancel, buttonValidate;

    private double latitude, longitude; // Pour le mode création
    private int placeId = -1;          // Pour le mode modification
    private boolean isCreationMode = false;

    private PlaceViewModel placeViewModel;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detail, container, false);
        editName = root.findViewById(R.id.editTextName);
        editDescription = root.findViewById(R.id.editTextDescription);
        editPhone = root.findViewById(R.id.editTextTelephone);
        editEmail = root.findViewById(R.id.editTextEmail);
        editWebsite = root.findViewById(R.id.editTextUrl);
        spinnerType = root.findViewById(R.id.spinnerType);
        buttonCancel = root.findViewById(R.id.buttonCancel);
        buttonValidate = root.findViewById(R.id.buttonValidate);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(requireActivity()).get(PlaceViewModel.class);

        Bundle args = getArguments();
        if (args != null) {
            if (args.containsKey("placeId")) {
                placeId = args.getInt("placeId", -1);
                isCreationMode = false;

                placeViewModel.getPlaceById(placeId).observe(getViewLifecycleOwner(), pwd -> {
                    if (pwd != null) {
                        editName.setText(pwd.getPlace().getName());
                        editDescription.setText(pwd.getPlace().getDescription());
                        editPhone.setText(pwd.getPlace().getPhoneNumber());
                        editEmail.setText(pwd.getPlace().getEmail());
                        editWebsite.setText(pwd.getPlace().getWebsite());

                        PlaceType type = pwd.getPlace().getType();
                        // Il faut adapter si tu utilises un ArrayAdapter<PlaceType>
                        // Trouver l'index
                        // ...
                    }
                });
            }
            else if (args.containsKey("latitude") && args.containsKey("longitude")) {
                latitude = args.getDouble("latitude");
                longitude = args.getDouble("longitude");
                isCreationMode = true;
            }
        }
        ArrayAdapter<PlaceType> typeAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                PlaceType.values()
        );
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(typeAdapter);
        buttonValidate.setOnClickListener(v -> onValidateClicked());

    }

    private void onValidateClicked() {

        String name = editName.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String website = editWebsite.getText().toString().trim();
        PlaceType selectedType = (PlaceType) spinnerType.getSelectedItem();


        if (isCreationMode) {
            PlaceEntity newPlace = new PlaceEntity(
                    0,
                    name,
                    description,
                    phone,
                    email,
                    website,
                    latitude,
                    longitude,
                    selectedType
            );

            ChildPlaceEntity details = null;
            switch (selectedType) {
                case PlaceToEat:
                    details = new PlaceToEatEntity();
                    // On peut initialiser priceRange, categories...
                    break;
                case PlaceToSleep:
                    details = new PlaceToSleepEntity();
                    break;
                case PlaceToGoOut:
                    details = new PlaceToGoOutEntity();
                    break;
                case PlaceToRelax:
                    details = new PlaceToRelaxEntity();
                    break;
                case PlaceToExercise:
                    details = new PlaceToExerciseEntity();
                    break;
                case CulturalPlace:
                    details = new CulturalPlaceEntity();
                    break;
            }


            placeViewModel.addPlace(newPlace, details);
            NavHostFragment.findNavController(DetailFragment.this).navigateUp();
        } else {
            // Modification
            // Charger l'entité existante (placeId)
            // On pourrait recharger depuis le ViewModel en synchrone,
            // ou on a déjà l'objet en mémoire dans un champ
            // => Adapter selon ton code.

            // Version simple : on refait un PlaceEntity
            PlaceEntity updatedPlace = new PlaceEntity(
                    placeId,
                    name,
                    description,
                    phone,
                    email,
                    website,
                    0,  // On n'a pas la lat/lng ?
                    0,  // => On doit re-charger depuis la base
                    selectedType
            );
            // Pareil pour l'entité fille
            ChildPlaceEntity newDetails = null;
            // ...
            // placeViewModel.updatePlace(updatedPlace, newDetails);
            // ...
            NavHostFragment.findNavController(this).navigateUp();
        }
    }
}