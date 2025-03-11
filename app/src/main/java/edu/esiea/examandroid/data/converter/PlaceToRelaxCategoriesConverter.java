package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.PlaceToRelaxCategories;


public class PlaceToRelaxCategoriesConverter {
    @TypeConverter
    public String fromPlaceToRelaxCategories(PlaceToRelaxCategories placeToRelax) {
        return (placeToRelax == null) ? null : placeToRelax.name();
    }

    @TypeConverter
    public PlaceToRelaxCategories toPlaceToRelaxCategories(String value) {
        return (value == null || value.isEmpty()) ? null : PlaceToRelaxCategories.valueOf(value);
    }
}
