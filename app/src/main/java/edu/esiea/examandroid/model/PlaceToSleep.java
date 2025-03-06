package edu.esiea.examandroid.model;

import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToSleep extends Place{

    private double minNightPrice;
    private String categories;

    public PlaceToSleep() {
    }

    public PlaceToSleep(int id, String name, String description, String phoneNumber, String email,
                        String website, double latitude, double longitude, PlaceType type,
                        String openingHours, double entryFee, String categories, double minNightPrice) {
        super(id, name, description, phoneNumber, email, website, latitude, longitude, type);
        this.categories = categories;
        this.minNightPrice = minNightPrice;
    }

    public double getMinNightPrice() {
        return minNightPrice;
    }

    public void setMinNightPrice(double minNightPrice) {
        this.minNightPrice = minNightPrice;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
