package edu.esiea.examandroid.data.dto;

import androidx.room.Embedded;
import androidx.room.Relation;

import edu.esiea.examandroid.data.entity.CulturalPlace;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEat;
import edu.esiea.examandroid.data.entity.PlaceToExercise;
import edu.esiea.examandroid.data.entity.PlaceToGoOut;
import edu.esiea.examandroid.data.entity.PlaceToRelax;
import edu.esiea.examandroid.data.entity.PlaceToSleep;

public class PlaceWithDetails {
    @Embedded
    private PlaceEntity place;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToEat placeToEat;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToSleep placeToSleep;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToGoOut placeToGoOut;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToRelax placeToRelax;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private PlaceToExercise placeToExercise;

    @Relation(parentColumn = "id", entityColumn = "placeId")
    private CulturalPlace culturalPlace;
}