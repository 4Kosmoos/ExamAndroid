package edu.esiea.examandroid.repository;

import android.content.Context;

import edu.esiea.examandroid.data.AppDatabase;
import edu.esiea.examandroid.data.dao.PlaceDao;
import edu.esiea.examandroid.data.dao.ChildPlaceEntity;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.data.executor.ExecutorProvider;

public class PlaceRepository {

    private final PlaceDao placeDao;
    private final ExecutorProvider executor;

    public PlaceRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        this.placeDao = db.placeDao();  // Initialisation correcte
        this.executor = ExecutorProvider.getInstance();    }

    public void addPlace(PlaceEntity place, ChildPlaceEntity details) {
        int placeId = (int) placeDao.insertPlace(place);
        details.setPlaceId(placeId);

        switch (place.getType()) {
            case PlaceToEat:
                placeDao.insertPlaceToEat((PlaceToEatEntity) details);
                break;
            case PlaceToSleep:
                placeDao.insertPlaceToSleep((PlaceToSleepEntity) details);
                break;
            case PlaceToGoOut:
                placeDao.insertPlaceToGoOut((PlaceToGoOutEntity) details);
                break;
            case PlaceToRelax:
                placeDao.insertPlaceToRelax((PlaceToRelaxEntity) details);
                break;
            case PlaceToExercise:
                placeDao.insertPlaceToExercise((PlaceToExerciseEntity) details);
                break;
            case CulturalPlace:
                placeDao.insertCulturalPlace((CulturalPlaceEntity) details);
                break;
            default:
                break;
        }
    }

    public PlaceWithDetails getPlaceWithDetails(int id) {
        return placeDao.getPlaceWithDetails(id);
    }

    public void removePlace(PlaceEntity place) {
        placeDao.deletePlace(place);
    }

    public void updatePlace(PlaceEntity updatedPlace, ChildPlaceEntity newDetails) {
        placeDao.deletePlace(updatedPlace);
        int newPlaceId = (int) placeDao.insertPlace(updatedPlace);
        newDetails.setPlaceId(newPlaceId);
        switch (updatedPlace.getType()) {
            case PlaceToEat:
                placeDao.insertPlaceToEat((PlaceToEatEntity) newDetails);
                break;
            case PlaceToSleep:
                placeDao.insertPlaceToSleep((PlaceToSleepEntity) newDetails);
                break;
            case PlaceToGoOut:
                placeDao.insertPlaceToGoOut((PlaceToGoOutEntity) newDetails);
                break;
            case PlaceToRelax:
                placeDao.insertPlaceToRelax((PlaceToRelaxEntity) newDetails);
                break;
            case PlaceToExercise:
                placeDao.insertPlaceToExercise((PlaceToExerciseEntity) newDetails);
                break;
            case CulturalPlace:
                placeDao.insertCulturalPlace((CulturalPlaceEntity) newDetails);
                break;
            default:
                break;
        }
    }
}

