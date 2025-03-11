package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.enums.SportPlaceCategories;

public class SportPlaceCategoriesConverter {
    @TypeConverter
    public String fromSportPlaceCategories(SportPlaceCategories sportPlace) {
        return (sportPlace == null) ? null : sportPlace.name();
    }

    @TypeConverter
    public SportPlaceCategories toSportPlaceCategories(String value) {
        return (value == null || value.isEmpty()) ? null : SportPlaceCategories.valueOf(value);
    }
}
