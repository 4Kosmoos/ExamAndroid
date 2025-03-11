package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.PlaceToSleepCategories;

public class PlaceToSleepCategoriesConverter {
    @TypeConverter
    public String fromPlaceToSleepCategories(PlaceToSleepCategories placeToSleep) {
        return (placeToSleep == null) ? null : placeToSleep.name();
    }

    @TypeConverter
    public PlaceToSleepCategories toPlaceToSleepCategories(String value) {
        return (value == null || value.isEmpty()) ? null : PlaceToSleepCategories.valueOf(value);
    }
}
