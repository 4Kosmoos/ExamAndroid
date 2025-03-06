package edu.esiea.examandroid.model;

import java.util.List;

public class CulturalPlace extends Place {
    private String openingHours;
    private double entryFee;
    private List<String> categories;

    public CulturalPlace() {
    }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public double getEntryFee() { return entryFee; }
    public void setEntryFee(double entryFee) { this.entryFee = entryFee; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
}
