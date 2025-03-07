package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;
import edu.esiea.examandroid.enums.PriceRange;

public class PriceRangeConverter {
    @TypeConverter
    public String fromPriceRange(PriceRange range) {
        return (range == null) ? null : range.name();
    }

    @TypeConverter
    public PriceRange toPriceRange(String value) {
        return (value == null || value.isEmpty()) ? null : PriceRange.valueOf(value);
    }
}
