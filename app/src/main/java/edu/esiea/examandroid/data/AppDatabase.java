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

//   method to insert fake data (not actually functional)

//    private static final Callback sRoomDatabaseCallback = new Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            ExecutorProvider.getInstance().getExecutor().execute(() -> {
//                PlaceDao dao = INSTANCE.placeDao();
//
//                //sample data 1
//                PlaceEntity place1 = new PlaceEntity(
//                        0,
//                        "Mon resto bidon",
//                        "Test description",
//                        "0102030405",
//                        "test@restaurant.fr",
//                        "www.test.fr",
//                        43.72,
//                        -1.05,
//                        PlaceType.PlaceToEat
//                );
//                long placeId = dao.insertPlace(place1);
//                PlaceToEatEntity details1 = new PlaceToEatEntity();
//                details1.setPlaceId((int) placeId);
//                details1.setPriceRange(PriceRange.Moyen);
//                details1.setCategories(Arrays.asList(EatCategories.Chinois,EatCategories.Libanais));
//                dao.insertPlaceToEat(details1);
//
//                // sample data 2
//                PlaceEntity place2 = new PlaceEntity(
//                        0,
//                        "Ma salle de sport",
//                        "Ouverte H24",
//                        "0987654321",
//                        "sport@test.com",
//                        "www.sport.fr",
//                        43.73,
//                        -1.06,
//                        PlaceType.PlaceToExercise
//                );
//                long placeId2 = dao.insertPlace(place2);
//
//                PlaceToExerciseEntity details2 = new PlaceToExerciseEntity();
//                details2.setPlaceId((int) placeId2);
//                details2.setCategories(Arrays.asList(SportCategories.PISCINE,SportCategories.SALLE_DE_MUSCU));
//                details2.setEntryFee(20);
//                details2.setMandatorySubscription(false);
//                details2.setOpeningHours("10h-22h");
//                dao.insertPlaceToExercise(details2);
//            });
//        }
//    };
}