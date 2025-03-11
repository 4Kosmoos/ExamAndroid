package edu.esiea.examandroid.repository;

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

public class PlaceRepository {

    private final PlaceDao placeDao;

    public PlaceRepository(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

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
}

