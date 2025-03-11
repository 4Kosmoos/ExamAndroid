package edu.esiea.examandroid.model;

import java.util.List;

import edu.esiea.examandroid.enums.SleepCategories;
import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToSleep extends Place{

    private double minNightPrice;
    private List<SleepCategories> categories;

    public PlaceToSleep() {
    }

    public PlaceToSleep(int id, String name, String description, String phoneNumber, String email,
                        String website, double latitude, double longitude, PlaceType type,
                        List<SleepCategories> categories, double minNightPrice) {
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

    public List<SleepCategories> getCategories() {
        return categories;
    }

    public void setCategories(List<SleepCategories> categories) {
        this.categories = categories;
    }
}
