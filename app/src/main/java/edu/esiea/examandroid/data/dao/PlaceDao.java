package edu.esiea.examandroid.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.CulturalPlace;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEat;
import edu.esiea.examandroid.data.entity.PlaceToExercise;
import edu.esiea.examandroid.data.entity.PlaceToGoOut;
import edu.esiea.examandroid.data.entity.PlaceToRelax;
import edu.esiea.examandroid.data.entity.PlaceToSleep;

@Dao
public interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPlace(PlaceEntity place);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToEat(PlaceToEat placeToEat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToSleep(PlaceToSleep placeToSleep);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToGoOut(PlaceToGoOut placeToGoOut);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToRelax(PlaceToRelax placeToRelax);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPlaceToExercise(PlaceToExercise placeToExercise);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCulturalPlace(CulturalPlace culturalPlace);

    @Transaction
    @Query("SELECT * FROM place WHERE id = :placeId")
    PlaceWithDetails getPlaceWithDetails(int placeId);

    @Delete
    void deletePlace(PlaceEntity place);
}