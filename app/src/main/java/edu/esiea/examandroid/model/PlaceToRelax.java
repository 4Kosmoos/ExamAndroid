package edu.esiea.examandroid.model;

import java.util.List;

import edu.esiea.examandroid.enums.RelaxCategories;
import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToRelax extends Place {

    private String openingHours;
    private double entryFee;
    private List<RelaxCategories> categories;

    public PlaceToRelax() {
    }

    public PlaceToRelax(int id, String name, String description, String phoneNumber, String email,
                        String website, double latitude, double longitude, PlaceType type,
                        String openingHours, double entryFee, List<RelaxCategories> categories) {
        super(id, name, description, phoneNumber, email, website, latitude, longitude, type);
        this.openingHours = openingHours;
        this.entryFee = entryFee;
        this.categories = categories;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public List<RelaxCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<RelaxCategories> categories) {
        this.categories = categories;
    }
}