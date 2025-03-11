package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.enums.PlaceToSleepCategories;

@Entity(
        tableName = "place_to_sleep",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToSleepEntity {

    @PrimaryKey
    private int placeId;
    private double minNightPrice;
    private List<PlaceToSleepCategories> categories;

    public PlaceToSleepEntity() {
    }

    public PlaceToSleepEntity(int placeId, double minNightPrice, List<PlaceToSleepCategories> categories) {
        this.placeId = placeId;
        this.minNightPrice = minNightPrice;
        this.categories = categories;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public double getMinNightPrice() { return minNightPrice; }
    public void setMinNightPrice(double minNightPrice) { this.minNightPrice = minNightPrice; }

    public List<PlaceToSleepCategories> getCategories() { return categories; }
    public void setCategories(List<PlaceToSleepCategories> categories) { this.categories = categories; }
}

