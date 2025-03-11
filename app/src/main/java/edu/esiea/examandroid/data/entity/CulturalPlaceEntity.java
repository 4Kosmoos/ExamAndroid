package edu.esiea.examandroid.data.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.List;

import edu.esiea.examandroid.data.dao.SpecificPlaceDao;
import edu.esiea.examandroid.enums.CulturalCategories;

@Entity(
        tableName = "cultural_place",
        foreignKeys = @ForeignKey(
                entity = PlaceEntity.class,
                parentColumns = "id",
                childColumns = "placeId",
                onDelete = CASCADE
        )
)
public class CulturalPlaceEntity implements SpecificPlaceDao {

    @PrimaryKey
    private int placeId;
    private List<CulturalCategories> categories;
    private String openingHours;
    private double entryFee;

    public CulturalPlaceEntity() {
    }

    public CulturalPlaceEntity(int placeId, String openingHours, double entryFee, List<CulturalCategories> categories) {
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

    public List<CulturalCategories> getCategories() { return categories; }
    public void setCategories(List<CulturalCategories> categories) { this.categories = categories; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }
}
