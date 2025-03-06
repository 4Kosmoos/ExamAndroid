package edu.esiea.examandroid.model;

import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToRelax extends Place {

    private String openingHours;
    private double entryFee;
    private String categories;

    public PlaceToRelax() {
    }

    public PlaceToRelax(int id, String name, String description, String phoneNumber, String email,
                        String website, double latitude, double longitude, PlaceType type,
                        String openingHours, double entryFee, String categories) {
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

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}