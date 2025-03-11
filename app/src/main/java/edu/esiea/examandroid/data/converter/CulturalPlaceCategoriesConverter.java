package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.CulturalPlaceCategories;

public class CulturalPlaceCategoriesConverter {
    @TypeConverter
    public String fromCulturalPlaceCategories(CulturalPlaceCategories culturalPlace) {
        return (culturalPlace == null) ? null : culturalPlace.name();
    }

    @TypeConverter
    public CulturalPlaceCategories toCulturalPlaceCategories(String value) {
        return (value == null || value.isEmpty()) ? null : CulturalPlaceCategories.valueOf(value);
    }
}
