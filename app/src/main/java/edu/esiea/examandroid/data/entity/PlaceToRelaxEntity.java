package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.enums.PlaceToRelaxCategories;

@Entity(
        tableName = "place_to_relax",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToRelaxEntity {

    @PrimaryKey
    private int placeId;
    private List<PlaceToRelaxCategories> categories;
    private String openingHours;
    private double entryFee;


    public PlaceToRelaxEntity() {
    }

    public PlaceToRelaxEntity(int placeId, String openingHours, double entryFee, List<PlaceToRelaxCategories> categories) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.entryFee = entryFee;
        this.categories = categories;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public List<PlaceToRelaxCategories> getCategories() { return categories; }
    public void setCategories(List<PlaceToRelaxCategories> categories) { this.categories = categories; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
}
