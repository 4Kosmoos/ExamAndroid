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

    public void setPlace(PlaceEntity place) { this.place = place; }
    public void setPlaceToEatEntity(PlaceToEatEntity placeToEatEntity) { this.placeToEatEntity = placeToEatEntity; }
    public void setPlaceToSleepEntity(PlaceToSleepEntity placeToSleepEntity) { this.placeToSleepEntity = placeToSleepEntity; }
    public void setPlaceToGoOutEntity(PlaceToGoOutEntity placeToGoOutEntity) { this.placeToGoOutEntity = placeToGoOutEntity; }
    public void setPlaceToRelaxEntity(PlaceToRelaxEntity placeToRelaxEntity) { this.placeToRelaxEntity = placeToRelaxEntity; }
    public void setPlaceToExerciseEntity(PlaceToExerciseEntity placeToExerciseEntity) { this.placeToExerciseEntity = placeToExerciseEntity; }
    public void setCulturalPlaceEntity(CulturalPlaceEntity culturalPlaceEntity) { this.culturalPlaceEntity = culturalPlaceEntity; }

    public PlaceEntity getPlace() { return place; }
    public PlaceToEatEntity getPlaceToEatEntity() { return placeToEatEntity; }
    public PlaceToSleepEntity getPlaceToSleepEntity() { return placeToSleepEntity; }
    public PlaceToGoOutEntity getPlaceToGoOutEntity() { return placeToGoOutEntity; }
    public PlaceToRelaxEntity getPlaceToRelaxEntity() { return placeToRelaxEntity; }
    public PlaceToExerciseEntity getPlaceToExerciseEntity() { return placeToExerciseEntity; }
    public CulturalPlaceEntity getCulturalPlaceEntity() { return culturalPlaceEntity; }

}