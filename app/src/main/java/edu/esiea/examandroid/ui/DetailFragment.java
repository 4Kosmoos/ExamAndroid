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
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.viewmodel.PlaceViewModel;


public class DetailFragment extends Fragment {

    private TextInputLayout inputLayoutName, inputLayoutDescription, inputLayoutTelephone, inputLayoutEmail, inputLayoutUrl;
    private TextInputEditText editTextName, editTextDescription, editTextTelephone, editTextEmail, editTextUrl;
    private Spinner spinnerType;
    private FrameLayout variableFieldsContainer;
    private Button buttonValidate, buttonCancel;

    private PlaceViewModel placeViewModel;
    private PlaceEntity currentPlace;
    private boolean isEditing = false;

    // pour PlaceToEat
    private Spinner spinnerPriceRange;      // pour PlaceToEat
    private TextInputEditText editTextCategories;

    // pour PlaceToSleep
    private TextInputEditText editTextMinimalPrice;

    public DetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        inputLayoutName = view.findViewById(R.id.inputLayoutName);
        inputLayoutDescription = view.findViewById(R.id.inputLayoutDescription);
        inputLayoutTelephone = view.findViewById(R.id.inputLayoutTelephone);
        inputLayoutEmail = view.findViewById(R.id.inputLayoutEmail);
        inputLayoutUrl = view.findViewById(R.id.inputLayoutUrl);
        editTextName = view.findViewById(R.id.editTextName);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextTelephone = view.findViewById(R.id.editTextTelephone);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextUrl = view.findViewById(R.id.editTextUrl);
        spinnerType = view.findViewById(R.id.spinnerType);
        variableFieldsContainer = view.findViewById(R.id.variableFieldsContainer);
        buttonValidate = view.findViewById(R.id.buttonValidate);
        buttonCancel = view.findViewById(R.id.buttonCancel);

        return view;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        placeViewModel = new ViewModelProvider(requireActivity()).get(PlaceViewModel.class);
//
//        ArrayAdapter<PlaceType> typeAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, PlaceType.values());
//        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerType.setAdapter(typeAdapter);
//
//        Bundle args = getArguments();
//        if (args != null && args.containsKey("placeId")) {
//            int placeId = args.getInt("placeId", -1);
//            if (placeId != -1) {
//                isEditing = true;
//                placeViewModel.getPlaceById(placeId).observe(getViewLifecycleOwner(), placeWithDetails -> {
//                    if (placeWithDetails != null) {
//                        currentPlace = placeWithDetails.getPlace();
//                        prepopulateCommonFields();
//                    }
//                });
//            }
//        }
//
//        spinnerType.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
//                variableFieldsContainer.removeAllViews();
//                PlaceType selectedType = (PlaceType) spinnerType.getSelectedItem();
//                if (selectedType == PlaceType.PlaceToEat) {
//                    View foodView = LayoutInflater.from(requireContext())
//                            .inflate(R.layout.partial_food_details, variableFieldsContainer, false);
//                    variableFieldsContainer.addView(foodView);
//                    setupFoodPartial(foodView);
//                    if (isEditing && currentPlace != null) {
//                        prepopulateFoodPartial(foodView);
//                    }
//                } else if (selectedType == PlaceType.PlaceToSleep) {
//                    View sleepView = LayoutInflater.from(requireContext())
//                            .inflate(R.layout.partial_sleep_details, variableFieldsContainer, false);
//                    variableFieldsContainer.addView(sleepView);
//                    setupSleepPartial(sleepView);
//                    if (isEditing && currentPlace != null) {
//                        prepopulateSleepPartial(sleepView);
//                    }
//                }
//                // à finir avec les autres types
//            }
//            @Override
//            public void onNothingSelected(android.widget.AdapterView<?> parent) {
//                variableFieldsContainer.removeAllViews();
//            }
//        });
//
//        buttonValidate.setOnClickListener(v -> validateAndSave());
//        buttonCancel.setOnClickListener(v -> NavHostFragment.findNavController(this).popBackStack());
//    }
//
//    private void prepopulateCommonFields() {
//        if (currentPlace != null) {
//            editTextName.setText(currentPlace.getName());
//            editTextDescription.setText(currentPlace.getDescription());
//            editTextTelephone.setText(currentPlace.getPhoneNumber());
//            editTextEmail.setText(currentPlace.getEmail());
//            editTextUrl.setText(currentPlace.getWebsite());
//            ArrayAdapter adapter = (ArrayAdapter) spinnerType.getAdapter();
//            int pos = adapter.getPosition(currentPlace.getType());
//            spinnerType.setSelection(pos);
//        }
//    }
//
//    private void setupFoodPartial(View foodView) {
//        spinnerPriceRange = foodView.findViewById(R.id.spinnerPriceRange);
//        editTextCategories = foodView.findViewById(R.id.editTextCategories);
//        ArrayAdapter<String> priceAdapter = new ArrayAdapter<>(requireContext(),
//                android.R.layout.simple_spinner_item, new String[]{"Abordable", "Moyen", "Luxe"});
//        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerPriceRange.setAdapter(priceAdapter);
//    }
//    private void prepopulateFoodPartial(View foodView) {
//        PlaceToEatEntity eatEntity = currentPlaceWithDetails.getPlaceToEat();
//        if (eatEntity == null) return; // Pas de détails pour ce type, on sort
//
//    }
//    private void setupSleepPartial(View sleepView) {
//        editTextMinimalPrice = sleepView.findViewById(R.id.editTextMinimalPrice);
//    }
//
//    private void prepopulateSleepPartial(View sleepView) {
//        // Pareil pour le sleep : préremplir editTextMinimalPrice si currentPlace a des sleep details.
//    }
//
//
//    private void validateAndSave() {
//        if (TextUtils.isEmpty(editTextName.getText())) {
//            Snackbar.make(requireView(), "Le nom est obligatoire", Snackbar.LENGTH_SHORT).show();
//            return;
//        }
//        String name = editTextName.getText().toString().trim();
//        String description = editTextDescription.getText().toString().trim();
//        String telephone = editTextTelephone.getText().toString().trim();
//        String email = editTextEmail.getText().toString().trim();
//        String website = editTextUrl.getText().toString().trim();
//        PlaceType type = (PlaceType) spinnerType.getSelectedItem();
//
//        PlaceEntity place;
//        if (isEditing && currentPlace != null) {
//            place = currentPlace;
//        } else {
//            place = new PlaceEntity();
//        }
//        place.setName(name);
//        place.setDescription(description);
//        place.setPhoneNumber(telephone);
//        place.setEmail(email);
//        place.setWebsite(website);
//        place.setType(type);
//        Bundle args = getArguments();
//        if (args != null && args.containsKey("latitude") && args.containsKey("longitude")) {
//            place.setLatitude(args.getDouble("latitude"));
//            place.setLongitude(args.getDouble("longitude"));
//        }
//
//        ChildPlaceEntity details = null;
//        if (type == PlaceType.PlaceToEat) {
//            PlaceToEatEntity foodDetails = new PlaceToEatEntity();
//
//            String price = spinnerPriceRange.getSelectedItem().toString();
//            try {
//                foodDetails.setPriceRange(PriceRange.valueOf(price));
//            } catch (Exception e) {
//                foodDetails.setPriceRange(PriceRange.Abordable);
//            }
//            String cats = (editTextCategories.getText() != null)
//                    ? editTextCategories.getText().toString().trim()
//                    : "";
//            List<EatCategories> catList = new ArrayList<>();
//            if (!cats.isEmpty()) {
//                String[] catArray = cats.split("\\s*,\\s*");
//                for (String cat : catArray) {
//                    try {
//                        //"Chinois","Libanais"
//                        catList.add(EatCategories.valueOf(cat));
//                    } catch (IllegalArgumentException e) {
//                    }
//                }
//            }
//            foodDetails.setCategories(catList);
//
//            details = foodDetails;
//        }
//
//
//        if (isEditing) {
//            placeViewModel.updatePlace(place, details);
//        } else {
//            placeViewModel.addPlace(place, details);
//        }
//        Snackbar.make(requireView(), "Enregistrement effectué", Snackbar.LENGTH_SHORT).show();
//        NavHostFragment.findNavController(this).popBackStack();
//    }
//
//    private double parseDoubleSafe(String value) {
//        try {
//            return Double.parseDouble(value.trim());
//        } catch (NumberFormatException e) {
//            return 0.0;
//        }
//    }
}