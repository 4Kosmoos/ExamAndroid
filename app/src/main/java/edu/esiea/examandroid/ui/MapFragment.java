package edu.esiea.examandroid.ui;

import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;

import edu.esiea.examandroid.R;
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

}