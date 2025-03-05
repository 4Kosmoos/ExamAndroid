package edu.esiea.examandroid.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import android.content.Context;
import edu.esiea.examandroid.data.dao.PlaceDao;
import edu.esiea.examandroid.data.entity.CulturalPlace;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEat;
import edu.esiea.examandroid.data.entity.PlaceToExercise;
import edu.esiea.examandroid.data.entity.PlaceToGoOut;
import edu.esiea.examandroid.data.entity.PlaceToRelax;
import edu.esiea.examandroid.data.entity.PlaceToSleep;

@Database(entities = {PlaceEntity.class,
        PlaceToEat.class,
        PlaceToSleep.class,
        PlaceToGoOut.class,
        PlaceToRelax.class,
        PlaceToExercise.class,
        CulturalPlace.class},
        version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract PlaceDao placeDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "places_db")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}