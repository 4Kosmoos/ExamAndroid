package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "place_to_sleep",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToSleep {

    @PrimaryKey
    private int placeId;
    private double minNightPrice;
    private String categories; // ex. "Hôtel, Airbnb, Auberge"

    public PlaceToSleep() {
    }

    public PlaceToSleep(int placeId, double minNightPrice, String categories) {
        this.placeId = placeId;
        this.minNightPrice = minNightPrice;
        this.categories = categories;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public double getMinNightPrice() { return minNightPrice; }
    public void setMinNightPrice(double minNightPrice) { this.minNightPrice = minNightPrice; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
}

