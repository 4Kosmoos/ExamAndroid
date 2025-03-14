package edu.esiea.examandroid.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    private double latitude, longitude;
    private int placeId = -1;          // Pour vérifier si on edit ou non
    private boolean isCreationMode = false;

    private PlaceViewModel placeViewModel;

//    private FrameLayout containerVariable;
//    private View partialView;

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
//        containerVariable = root.findViewById(R.id.containerVariable);

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
        spinnerType.setAdapter(typeAdapter);
////        spinnerType.setSelection(0);
////        showPartialForType((PlaceType) spinnerType.getSelectedItem());
////
////        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
////                PlaceType selectedType = (PlaceType) spinnerType.getSelectedItem();
////                showPartialForType(selectedType);
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) {}
////        });
//
        buttonValidate.setOnClickListener(v -> onValidateClicked());
        buttonCancel.setOnClickListener(v -> NavHostFragment.findNavController(this).navigateUp());
    }
//
//    private void showPartialForType(PlaceType type) {
//        containerVariable.removeAllViews();
//        LayoutInflater inflater = LayoutInflater.from(requireContext());
//
//        switch (type) {
//            case PlaceToEat:
//                partialView = inflater.inflate(R.layout.partial_eat, containerVariable, false);
//                containerVariable.addView(partialView);
//                setupPartialEat(partialView);
//                break;
//            case PlaceToSleep:
////                partialView = inflater.inflate(R.layout.partial_sleep, containerVariable, false);
////                containerVariable.addView(partialView);
////                break;
//        }
//    }
//
//    private void setupPartialEat(View partialView) {
//        Spinner spinnerPriceRangeEat = partialView.findViewById(R.id.spinnerPriceRangeEat);
//        ArrayAdapter<PriceRange> priceAdapter = new ArrayAdapter<>(
//                requireContext(),
//                android.R.layout.simple_spinner_item,
//                PriceRange.values()
//        );
//        spinnerPriceRangeEat.setAdapter(priceAdapter);
//    }

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
                    //details = buildEatDetails();
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

//            Log.d("DetailFragment", "Nouvelle place : " + newPlace);
//            Log.d("DetailFragment", "Détails : " + details);
            placeViewModel.addPlace(newPlace, details);
            NavHostFragment.findNavController(DetailFragment.this).navigateUp();
        } else {
            // Modification PAS ENCORE IMPLÉMENTÉE

            PlaceEntity updatedPlace = new PlaceEntity(
                    placeId,
                    name,
                    description,
                    phone,
                    email,
                    website,
                    0,  // censé garder la latitude, longitude ( on verra plus tard)
                    0,
                    selectedType
            );
            ChildPlaceEntity newDetails = null;
            // placeViewModel.updatePlace(updatedPlace, newDetails);
            NavHostFragment.findNavController(this).navigateUp();
        }
    }
//    private PlaceToEatEntity buildEatDetails() {
//        Spinner spinnerPriceRangeEat = partialView.findViewById(R.id.spinnerPriceRangeEat);
//        EditText editEatCategories = partialView.findViewById(R.id.editEatCategories);
//
//        PlaceToEatEntity detailsEat = new PlaceToEatEntity();
//        PriceRange selectedPriceRange = (PriceRange) spinnerPriceRangeEat.getSelectedItem();
//        detailsEat.setPriceRange(selectedPriceRange);
//
//        String catsStr = editEatCategories.getText().toString().trim();
//        List<EatCategories> catList = parseEatCategories(catsStr);
//        detailsEat.setCategories(catList);
//        Log.d("DetailFragment", "PriceRange sélectionné : " + selectedPriceRange);
//        Log.d("DetailFragment", "Catégories saisies : " + catsStr);
//        Log.d("DetailFragment", "Détails construits : " + detailsEat);
//        return detailsEat;
//    }
//
//
//    private List<EatCategories> parseEatCategories(String catsStr) {
//        List<EatCategories> list = new ArrayList<>();
//        if (catsStr.isEmpty()) {
//            return list;
//        }
//        String[] splitted = catsStr.split(",");
//        for (String rawCat : splitted) {
//            rawCat = rawCat.trim();
//            try {
//                EatCategories cat = EatCategories.valueOf(rawCat);
//                list.add(cat);
//            } catch (IllegalArgumentException e) {
//            }
//        }
//        return list;
//    }

}