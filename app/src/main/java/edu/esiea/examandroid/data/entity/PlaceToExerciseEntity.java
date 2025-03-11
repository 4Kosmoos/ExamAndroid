package edu.esiea.examandroid.data.entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.data.dao.ChildPlaceEntity;
import edu.esiea.examandroid.enums.SportCategories;

@Entity(
        tableName = "place_to_Exercise",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToExerciseEntity implements ChildPlaceEntity {
    @PrimaryKey
    private int placeId;
    private List<SportCategories> categories;
    private String openingHours;
    private double entryFee;
    private boolean mandatorySubscription;

    public PlaceToExerciseEntity() {
    }

    public PlaceToExerciseEntity(int placeId, String openingHours, double entryFee, List<SportCategories> categories, boolean mandatorySubscription) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.categories = categories;
        this.entryFee = entryFee;
        this.mandatorySubscription = mandatorySubscription;
    }

    @Override
    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }
    public int getPlaceId() {
        return placeId;
    }

    public List<SportCategories> getCategories() { return categories; }
    public void setCategories(List<SportCategories> categories) { this.categories = categories; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    public boolean getMandatorySubscription(){return mandatorySubscription;}
    public void setMandatorySubscription(boolean mandatorySubscription){this.mandatorySubscription= mandatorySubscription;}
}
