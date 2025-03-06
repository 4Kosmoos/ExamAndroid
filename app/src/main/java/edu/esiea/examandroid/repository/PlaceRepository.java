package edu.esiea.examandroid.repository;

import edu.esiea.examandroid.data.dao.PlaceDao;
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

    public void addPlace(PlaceEntity place, Object details) {
        long placeId = placeDao.insertPlace(place);

    // permet de faire le lien entre entre l’ID auto-généré du parent et la clé étrangère dans la table fille(Room le fait pas auto)
    //TODO : opti cette méthode.
        if (details instanceof PlaceToEatEntity) {
            ((PlaceToEatEntity) details).setPlaceId((int) placeId);
        } else if (details instanceof PlaceToSleepEntity) {
            ((PlaceToSleepEntity) details).setPlaceId((int) placeId);
        } else if (details instanceof PlaceToGoOutEntity) {
            ((PlaceToGoOutEntity) details).setPlaceId((int) placeId);
        } else if (details instanceof PlaceToRelaxEntity) {
            ((PlaceToRelaxEntity) details).setPlaceId((int) placeId);
        } else if (details instanceof PlaceToExerciseEntity) {
            ((PlaceToExerciseEntity) details).setPlaceId((int) placeId);
        } else if (details instanceof CulturalPlaceEntity) {
            ((CulturalPlaceEntity) details).setPlaceId((int) placeId);
        }

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

