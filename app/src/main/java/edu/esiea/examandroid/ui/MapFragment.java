package edu.esiea.examandroid.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;

import edu.esiea.examandroid.R;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.enums.PlaceType;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        placeViewModel = new ViewModelProvider(requireActivity()).get(PlaceViewModel.class);

        placeViewModel.getMovingPlaceId().observe(getViewLifecycleOwner(), movingId -> {
        });

        MapEventsOverlay eventsOverlay = new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                Integer movingId = placeViewModel.getMovingPlaceId().getValue();
                if (movingId != null && movingId != -1) {
                    placeViewModel.movePlaceCoordinates(movingId, p.getLatitude(), p.getLongitude());
                    placeViewModel.setMovingPlaceId(-1);
                    Toast.makeText(requireContext(),
                            "Lieu déplacé à " + p.getLatitude() + ", " + p.getLongitude(),
                            Toast.LENGTH_SHORT).show();
                    return true;
                }else {
                    Bundle args = new Bundle();
                    args.putDouble("latitude", p.getLatitude());
                    args.putDouble("longitude", p.getLongitude());
                    NavHostFragment.findNavController(MapFragment.this)
                            .navigate(R.id.action_map_to_detail, args);
                    return true;
                }
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        });
        mapView.getOverlays().add(eventsOverlay);


        placeViewModel.getAllPlacesLive().observe(getViewLifecycleOwner(), places -> {
            mapView.getOverlays().clear();
            mapView.getOverlays().add(eventsOverlay);

            for (PlaceWithDetails pwd : places) {
                PlaceEntity place = pwd.getPlace();
                Marker marker = new Marker(mapView);
                marker.setPosition(new GeoPoint(place.getLatitude(), place.getLongitude()));
                marker.setTitle(place.getName());
                marker.setSnippet(place.getDescription());

                PlaceType type = place.getType();
                Drawable iconDrawable = getIconDrawable(type);
                Drawable scaledDrawable = getScaledDrawable(iconDrawable, 0.05f);
                marker.setIcon(scaledDrawable);
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);

                marker.setOnMarkerClickListener((m, mapView) -> {
                    showMarkerInfoDialog(place);
                    return true;
                });
                mapView.getOverlays().add(marker);
            }
            mapView.invalidate();
        });
    }

    private Drawable getIconDrawable(PlaceType type) {
        switch (type) {
            case PlaceToEat:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_eat);
            case PlaceToSleep:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_sleep);
            case PlaceToGoOut:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_out);
            case PlaceToRelax:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_relax);
            case PlaceToExercise:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_sport);
            case CulturalPlace:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_cultural);
            default:
                return ContextCompat.getDrawable(requireContext(), R.drawable.ic_other);
        }
    }
    private Drawable getScaledDrawable(Drawable drawable, float scale) {
        if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        int width = (int) (bitmap.getWidth() * scale);
        int height = (int) (bitmap.getHeight() * scale);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        return new BitmapDrawable(getResources(), scaledBitmap);
    }

    private void showMarkerInfoDialog(PlaceEntity place) {
        new AlertDialog.Builder(requireContext())
                .setTitle(place.getName())
                .setMessage(place.getDescription())
                .setPositiveButton("Éditer", (dialog, which) -> {
                    Bundle args = new Bundle();
                    args.putInt("placeId", place.getId());
                    NavHostFragment.findNavController(MapFragment.this)
                            .navigate(R.id.action_map_to_detail, args);
                })
                .setNegativeButton("Supprimer", (dialog, which) -> {
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Confirmer la suppression")
                            .setMessage("Voulez-vous vraiment supprimer ce lieu ?")
                            .setNegativeButton("Annuler", (d, w) -> d.dismiss())
                            .setPositiveButton("Supprimer", (d, w) -> {
                                placeViewModel.removePlace(place);
                                d.dismiss();
                            })
                            .show();
                })
                .setNeutralButton("Déplacer", (dialog, which) -> {
                    placeViewModel.setMovingPlaceId(place.getId());
                    Toast.makeText(requireContext(), "Cliquez sur la carte pour repositionner ce lieu", Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}