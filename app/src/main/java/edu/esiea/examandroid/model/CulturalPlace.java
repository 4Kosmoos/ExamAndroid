package edu.esiea.examandroid.model;

import java.util.List;

import edu.esiea.examandroid.enums.CulturalPlaceCategories;

public class CulturalPlace extends Place {
    private String openingHours;
    private double entryFee;
    private List<CulturalPlaceCategories> categories;

    public CulturalPlace() {
    }

    public CulturalPlace(String openingHours, double entryFee, List<CulturalPlaceCategories> categories) {
        this.openingHours = openingHours;
        this.entryFee = entryFee;
        this.categories = categories;
    }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    public List<CulturalPlaceCategories> getCategories() { return categories; }
    public void setCategories(List<CulturalPlaceCategories> categories) { this.categories = categories; }
}
