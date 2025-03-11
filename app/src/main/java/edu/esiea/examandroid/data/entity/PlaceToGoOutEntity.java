package edu.esiea.examandroid.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

import java.util.List;

import edu.esiea.examandroid.enums.PlaceToGoOutCategories;

@Entity(
        tableName = "place_to_go_out",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)

public class PlaceToGoOutEntity {

    @PrimaryKey
    private int placeId;

    private String openingHours;
    private double entryFee;     // 0 => entrée gratuite
    private List<PlaceToGoOutCategories> categories;

    public PlaceToGoOutEntity() {
    }

    public PlaceToGoOutEntity(int placeId, String openingHours, double entryFee, List<PlaceToGoOutCategories> categories) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.entryFee = entryFee;
        this.categories = categories;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    public List<PlaceToGoOutCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<PlaceToGoOutCategories> categories) {
        this.categories = categories;
    }
}
