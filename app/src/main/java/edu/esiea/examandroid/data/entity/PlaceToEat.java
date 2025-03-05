package edu.esiea.examandroid.data.entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(
        tableName = "place_to_eat",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToEat {
    @PrimaryKey
    private int placeId;

    private String priceRange;
    private String categories;

    public PlaceToEat() {
    }

    public PlaceToEat(int placeId, String priceRange, String categories) {
        this.placeId = placeId;
        this.priceRange = priceRange;
        this.categories = categories;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }
}
