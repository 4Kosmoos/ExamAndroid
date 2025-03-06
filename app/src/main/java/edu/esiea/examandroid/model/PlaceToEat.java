package edu.esiea.examandroid.model;

import java.util.List;
import edu.esiea.examandroid.enums.PlaceType;

public class PlaceToEat extends Place {
    private String priceRange;
    private List<String> categories;

    public PlaceToEat() {
    }

    public PlaceToEat(int id, String name, String description, String phoneNumber, String email,
                      String website, double latitude, double longitude, PlaceType type,
                      String priceRange, List<String> categories) {
        super(id, name, description, phoneNumber, email, website, latitude, longitude, type);
        this.priceRange = priceRange;
        this.categories = categories;
    }

    public String getPriceRange() { return priceRange; }
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
}
