package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.data.dao.SpecificPlaceDao;
import edu.esiea.examandroid.enums.SleepCategories;

@Entity(
        tableName = "place_to_sleep",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToSleepEntity implements SpecificPlaceDao {

    @PrimaryKey
    private int placeId;
    private double minNightPrice;
    private List<SleepCategories> categories;

    public PlaceToSleepEntity() {
    }

    public PlaceToSleepEntity(int placeId, double minNightPrice, List<SleepCategories> categories) {
        this.placeId = placeId;
        this.minNightPrice = minNightPrice;
        this.categories = categories;
    }

    @Override
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    public int getPlaceId() {
        return placeId;
    }

    public double getMinNightPrice() { return minNightPrice; }
    public void setMinNightPrice(double minNightPrice) { this.minNightPrice = minNightPrice; }

    public List<SleepCategories> getCategories() { return categories; }
    public void setCategories(List<SleepCategories> categories) { this.categories = categories; }
}

