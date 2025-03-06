package edu.esiea.examandroid.data.dto;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;

public class PlaceWithDetails {
    @Embedded
    private PlaceEntity place;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToEatEntity placeToEatEntity;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToSleepEntity placeToSleepEntity;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToGoOutEntity placeToGoOutEntity;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToRelaxEntity placeToRelaxEntity;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToExerciseEntity placeToExerciseEntity;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private CulturalPlaceEntity culturalPlaceEntity;

    public PlaceEntity getPlace() {
        return place;
    }

    public PlaceToEatEntity getPlaceToEat() {
        return placeToEatEntity;
    }

    public PlaceToSleepEntity getPlaceToSleep() {
        return placeToSleepEntity;
    }

    public PlaceToGoOutEntity getPlaceToGoOut() {
        return placeToGoOutEntity;
    }

    public PlaceToRelaxEntity getPlaceToRelax() {
        return placeToRelaxEntity;
    }

    public PlaceToExerciseEntity getPlaceToExercise() {
        return placeToExerciseEntity;
    }

    public CulturalPlaceEntity getCulturalPlace() {
        return culturalPlaceEntity;
    }

}