package edu.esiea.examandroid.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.executor.ExecutorProvider;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.enums.SportCategories;
import edu.esiea.examandroid.repository.PlaceRepository;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepository repository;
    private MutableLiveData<List<PlaceWithDetails>> allPlacesLiveData;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        repository = new PlaceRepository(application);
        allPlacesLiveData = new MutableLiveData<>();
        loadAllPlaces();

        // fake data pour tester
        ExecutorProvider.getInstance().getExecutor().execute(() -> {
            List<PlaceWithDetails> existing = repository.getAllPlaces();
            if (existing == null || existing.isEmpty()) {
                insertFakeData();
                loadAllPlaces();
            }
        });
    }

    private void insertFakeData() {

        // sample data 1
        PlaceEntity place1 = new PlaceEntity(
                0,
                "Resto test",
                "Desc test",
                "0102030405",
                "test@bidon.fr",
                "www.bidon.fr",
                43.72,
                -1.05,
                PlaceType.PlaceToEat
        );
        PlaceToEatEntity details1 = new PlaceToEatEntity();
        details1.setPriceRange(PriceRange.Abordable);
        details1.setCategories(Arrays.asList(EatCategories.Chinois,EatCategories.Libanais));
        repository.addPlace(place1, details1);

        // sample data 2
        PlaceEntity place2 = new PlaceEntity(
                0,
                "Ma salle de sport",
                "Ouverte H24",
                "0987654321",
                "sport@test.com",
                "www.sport.fr",
                43.73,
                -1.06,
                PlaceType.PlaceToExercise
        );
        PlaceToExerciseEntity details2 = new PlaceToExerciseEntity();
        details2.setCategories(Arrays.asList(SportCategories.PISCINE,SportCategories.SALLE_DE_MUSCU));
        details2.setEntryFee(20);
        details2.setMandatorySubscription(false);
        details2.setOpeningHours("10h-22h");
        repository.addPlace(place2, details2);
    }

    private void loadAllPlaces() {
        ExecutorProvider.getInstance().getExecutor().execute(() -> {
            List<PlaceWithDetails> list = repository.getAllPlaces();
            allPlacesLiveData.postValue(list);
        });
    }

    public LiveData<List<PlaceWithDetails>> getAllPlacesLive() {
        return allPlacesLiveData;
    }

    public void removePlace(PlaceEntity place) {
        ExecutorProvider.getInstance().getExecutor().execute(() -> {
            repository.removePlace(place);
            loadAllPlaces();
        });
    }

    public LiveData<PlaceWithDetails> getPlaceById(int id) {
        MutableLiveData<PlaceWithDetails> liveData = new MutableLiveData<>();
        ExecutorProvider.getInstance().getExecutor().execute(() -> {
            PlaceWithDetails pwd = repository.getPlaceWithDetails(id);
            liveData.postValue(pwd);
        });
        return liveData;
    }

}