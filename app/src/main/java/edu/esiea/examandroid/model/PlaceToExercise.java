package edu.esiea.examandroid.model;

import java.util.List;
import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToExercise extends Place {

    private String categories;
    private String openingHours;
    private double entryFee;
    private boolean mandatorySubscription;

    public PlaceToExercise() {
    }

    public PlaceToExercise(int id, String name, String description, String phoneNumber, String email,
                      String website, double latitude, double longitude, PlaceType type,
                      String openingHours, String categories, double entryFee, boolean mandatorySubscription) {
        super(id, name, description, phoneNumber, email, website, latitude, longitude, type);
        this.openingHours = openingHours;
        this.categories = categories;
        this.entryFee = entryFee;
        this.mandatorySubscription = mandatorySubscription;
    }

    public boolean isMandatorySubscription() {
        return mandatorySubscription;
    }

    public void setMandatorySubscription(boolean mandatorySubscription) {
        this.mandatorySubscription = mandatorySubscription;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
