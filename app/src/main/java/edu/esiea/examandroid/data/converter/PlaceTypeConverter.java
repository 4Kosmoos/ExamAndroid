package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import edu.esiea.examandroid.enums.PlaceType;

public class PlaceTypeConverter {

    @TypeConverter
    public static String fromPlaceType(PlaceType type) {
        return type == null ? null : type.name();
    }

    @TypeConverter
    public static PlaceType toPlaceType(String name) {
        if (name == null) {
            return null;
        }
        else {
            return PlaceType.valueOf(name);
        }
    }
}
