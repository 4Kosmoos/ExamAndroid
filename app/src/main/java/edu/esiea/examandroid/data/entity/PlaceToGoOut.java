package edu.esiea.examandroid.data.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "place_to_go_out",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)

public class PlaceToGoOut {

    @PrimaryKey
    private int placeId;

    private String openingHours; // ex. "10:00 - 02:00"
    private double entryFee;     // 0 => entrée gratuite

    public PlaceToGoOut() {
    }

    public PlaceToGoOut(int placeId, String openingHours, double entryFee) {
        this.placeId = placeId;
        this.openingHours = openingHours;
        this.entryFee = entryFee;
    }

    public int getPlaceId() { return placeId; }
    public void setPlaceId(int placeId) { this.placeId = placeId; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
}
