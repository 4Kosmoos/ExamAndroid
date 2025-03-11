package edu.esiea.examandroid.data.converter;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.List;

import edu.esiea.examandroid.enums.GoOutCategories;


public class GoOutCategoriesConverter {
    @TypeConverter
    public String fromGoOutCategories(List<GoOutCategories> categories) {
        if (categories == null || categories.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            sb.append(categories.get(i).name());
            if (i < categories.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();    }

    @TypeConverter
    public List<GoOutCategories> toGoOutCategories(String data) {
        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }
        String[] names = data.split(",");
        List<GoOutCategories> list = new ArrayList<>();
        for (String name : names) {
            list.add(GoOutCategories.valueOf(name));
        }
        return list;
    }
}
