package edu.esiea.examandroid.ui;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import edu.esiea.examandroid.R;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.model.Place;
import edu.esiea.examandroid.viewmodel.PlaceViewModel;

public class MapFragment extends Fragment {

    private Button buttonGoToList;
    private MapView mapView;
    private PlaceViewModel placeViewModel;

    private ActivityResultLauncher<String> requestLocationPermissionLauncher;

    private static final GeoPoint DEFAULT_CENTER = new GeoPoint(43.7196087, -1.0549204); // ESIEA
    private static final double DEFAULT_ZOOM = 15.0;

    public MapFragment() {
    }

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {

    Configuration.getInstance().load(
            requireContext(),
            PreferenceManager.getDefaultSharedPreferences(requireContext())
    );
    View view = inflater.inflate(R.layout.fragment_map, container, false);

    mapView = view.findViewById(R.id.map);
    mapView.setTileSource(TileSourceFactory.MAPNIK);
    mapView.setMultiTouchControls(true);
    mapView.getController().setZoom(DEFAULT_ZOOM);
    mapView.getController().setCenter(DEFAULT_CENTER);

    buttonGoToList = view.findViewById(R.id.button_go_to_list);
    buttonGoToList.setOnClickListener(v -> {
        NavController navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.listFragment);
    });

    return view;
}

//    private void addMarker(Place place) {
//        Marker marker = new Marker(mapView);
//        marker.setPosition(new GeoPoint(place.getLatitude(), place.getLongitude()));
//        marker.setTitle(place.getName());
//        marker.setSnippet(place.getDescription());
//        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
//
//        marker.setOnMarkerClickListener((m, mapView) -> {
//
//            Bundle args = new Bundle();
//            args.putInt("placeId", place.getId());
//            NavHostFragment.findNavController(this)
//                    .navigate(R.id.action_map_to_detail, args);
//            return true;
//        });
//
//        mapView.getOverlays().add(marker);
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(requireActivity()).get(PlaceViewModel.class);

        placeViewModel.getAllPlacesLive().observe(getViewLifecycleOwner(), places -> {
            mapView.getOverlays().clear();

            for (PlaceWithDetails pwd : places) {
                PlaceEntity place = pwd.getPlace();
                Marker marker = new Marker(mapView);
                marker.setPosition(new GeoPoint(place.getLatitude(), place.getLongitude()));
                marker.setTitle(place.getName());
                marker.setSnippet(place.getDescription());
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setOnMarkerClickListener((m, mapView) -> {
                    Bundle args = new Bundle();
                    args.putInt("placeId", place.getId());
                    NavHostFragment.findNavController(MapFragment.this)
                            .navigate(R.id.action_map_to_detail, args);
                    return true;
                });
                mapView.getOverlays().add(marker);
            }
            mapView.invalidate();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }
}