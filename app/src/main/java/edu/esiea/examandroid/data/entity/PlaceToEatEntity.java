package edu.esiea.examandroid.data.entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

import java.util.List;

import edu.esiea.examandroid.data.dao.ChildPlaceEntity;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PriceRange;

@Entity(
        tableName = "place_to_eat",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToEatEntity implements ChildPlaceEntity {
    @PrimaryKey
    private int placeId;

    private PriceRange priceRange;
    private List<EatCategories> categories;

    public PlaceToEatEntity() {
    }

    public PlaceToEatEntity(int placeId, PriceRange priceRange, List<EatCategories> categories) {
        this.placeId = placeId;
        this.priceRange = priceRange;
        this.categories = categories;
    }

    @Override
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    public int getPlaceId() {
        return placeId;
    }

    public PriceRange getPriceRange() { return priceRange; }
    public void setPriceRange(PriceRange priceRange) { this.priceRange = priceRange; }

    public List<EatCategories> getCategories() { return categories; }
    public void setCategories(List<EatCategories> categories) { this.categories = categories; }
}
