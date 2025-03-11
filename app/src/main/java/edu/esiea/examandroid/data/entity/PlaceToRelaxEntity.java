package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.data.dao.ChildPlaceEntity;
import edu.esiea.examandroid.enums.RelaxCategories;

@Entity(
        tableName = "place_to_relax",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToRelaxEntity implements ChildPlaceEntity {

    @PrimaryKey
    private int placeId;
    private List<RelaxCategories> categories;
    private String openingHours;
    private double entryFee;


    public PlaceToRelaxEntity() {
    }

    public PlaceToRelaxEntity(int placeId, String openingHours, double entryFee, List<RelaxCategories> categories) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.entryFee = entryFee;
        this.categories = categories;
    }

    @Override
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    public int getPlaceId() {
        return placeId;
    }

    public List<RelaxCategories> getCategories() { return categories; }
    public void setCategories(List<RelaxCategories> categories) { this.categories = categories; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
}
