package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.PlaceToGoOutCategories;


public class PlaceToGoOutCategoriesConverter {
    @TypeConverter
    public String fromPlaceToGoOutCategories(PlaceToGoOutCategories placeToGoOut) {
        return (placeToGoOut == null) ? null : placeToGoOut.name();
    }

    @TypeConverter
    public PlaceToGoOutCategories toPlaceToGoOutCategories(String value) {
        return (value == null || value.isEmpty()) ? null : PlaceToGoOutCategories.valueOf(value);
    }
}
