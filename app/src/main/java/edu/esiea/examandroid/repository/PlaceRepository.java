package edu.esiea.examandroid.repository;

import edu.esiea.examandroid.data.dao.PlaceDao;
import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.CulturalPlace;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEat;
import edu.esiea.examandroid.data.entity.PlaceToExercise;
import edu.esiea.examandroid.data.entity.PlaceToGoOut;
import edu.esiea.examandroid.data.entity.PlaceToRelax;
import edu.esiea.examandroid.data.entity.PlaceToSleep;

public class PlaceRepository {

    private final PlaceDao placeDao;

    public PlaceRepository(PlaceDao placeDao) {
        this.placeDao = placeDao;
    }

    public void addPlace(PlaceEntity place, Object details) {
        // Insérer l'entité principale
        long placeId = placeDao.insertPlace(place);

        // En fonction du type, insérer l'entité spécifique
        switch (place.getType()) {
            case "PlaceToEat":
                placeDao.insertPlaceToEat((PlaceToEat) details);
                break;
            case "PlaceToSleep":
                placeDao.insertPlaceToSleep((PlaceToSleep) details);
                break;
            case "PlaceToGoOut":
                placeDao.insertPlaceToGoOut((PlaceToGoOut) details);
                break;
            case "PlaceToRelax":
                placeDao.insertPlaceToRelax((PlaceToRelax) details);
                break;
            case "PlaceToExercise":
                placeDao.insertPlaceToExercise((PlaceToExercise) details);
                break;
            case "CulturalPlace":
                placeDao.insertCulturalPlace((CulturalPlace) details);
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

