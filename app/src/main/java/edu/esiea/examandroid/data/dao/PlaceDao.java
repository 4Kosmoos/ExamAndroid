package edu.esiea.examandroid.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.enums.PlaceType;

@Dao
public interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPlace(PlaceEntity place);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToEat(PlaceToEatEntity placeToEatEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToSleep(PlaceToSleepEntity placeToSleepEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToGoOut(PlaceToGoOutEntity placeToGoOutEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToRelax(PlaceToRelaxEntity placeToRelaxEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToExercise(PlaceToExerciseEntity placeToExerciseEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCulturalPlace(CulturalPlaceEntity culturalPlaceEntity);

    @Transaction
    @Query("SELECT * FROM place WHERE id = :placeId")
    PlaceWithDetails getPlaceWithDetails(int placeId);

    @Delete
    void deletePlace(PlaceEntity place);

    @Transaction
    @Query("SELECT * FROM place")
    List<PlaceWithDetails> getAllPlaces();

    @Transaction
    @Query("SELECT * FROM place WHERE type = :type")
    List<PlaceWithDetails> getPlacesByType(PlaceType type);
}
