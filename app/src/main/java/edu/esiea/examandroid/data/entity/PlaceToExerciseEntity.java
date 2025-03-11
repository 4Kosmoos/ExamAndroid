package edu.esiea.examandroid.data.entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.enums.SportPlaceCategories;

@Entity(
        tableName = "place_to_Exercise",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class PlaceToExerciseEntity {
    @PrimaryKey
    private int placeId;
    private List<SportPlaceCategories> categories;
    private String openingHours;
    private double entryFee;
    private boolean mandatorySubscription;

    public PlaceToExerciseEntity() {
    }

    public PlaceToExerciseEntity(int placeId, String openingHours, double entryFee, List<SportPlaceCategories> categories, boolean mandatorySubscription) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.categories = categories;
        this.entryFee = entryFee;
        this.mandatorySubscription = mandatorySubscription;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public List<SportPlaceCategories> getCategories() { return categories; }
    public void setCategories(List<SportPlaceCategories> categories) { this.categories = categories; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    public boolean getMandatorySubscription(){return mandatorySubscription;}
    public void setMandatorySubscription(boolean mandatorySubscription){this.mandatorySubscription= mandatorySubscription;}
}
