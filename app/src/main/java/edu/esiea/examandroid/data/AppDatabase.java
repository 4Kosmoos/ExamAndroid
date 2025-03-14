package edu.esiea.examandroid.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.data.converter.CulturalCategoriesConverter;
import edu.esiea.examandroid.data.converter.EatCategoriesListConverter;
import edu.esiea.examandroid.data.converter.GoOutCategoriesConverter;
import edu.esiea.examandroid.data.converter.PriceRangeConverter;
import edu.esiea.examandroid.data.converter.RelaxCategoriesConverter;
import edu.esiea.examandroid.data.converter.SleepCategoriesConverter;
import edu.esiea.examandroid.data.converter.SportCategoriesConverter;
import edu.esiea.examandroid.data.dao.PlaceDao;
import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.data.executor.ExecutorProvider;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.enums.SportCategories;

@Database(entities = {PlaceEntity.class,
        PlaceToEatEntity.class,
        PlaceToSleepEntity.class,
        PlaceToGoOutEntity.class,
        PlaceToRelaxEntity.class,
        PlaceToExerciseEntity.class,
        CulturalPlaceEntity.class},
        version = 1)
@TypeConverters({
        PriceRangeConverter.class,
        EatCategoriesListConverter.class,
        SleepCategoriesConverter.class,
        GoOutCategoriesConverter.class,
        RelaxCategoriesConverter.class,
        SportCategoriesConverter.class,
        CulturalCategoriesConverter.class
})
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