package edu.esiea.examandroid.model;

import java.util.List;

import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;

public class PlaceToEat extends Place {
    private PriceRange priceRange;
    private List<EatCategories> categories;

    public PlaceToEat() {
    }

    public PlaceToEat(int id, String name, String description, String phoneNumber, String email,
                      String website, double latitude, double longitude, PlaceType type,
                      PriceRange priceRange, List<EatCategories> categories) {
        super(id, name, description, phoneNumber, email, website, latitude, longitude, type);
        this.priceRange = priceRange;
        this.categories = categories;
    }

    public PriceRange getPriceRange() { return priceRange; }
    public void setPriceRange(PriceRange priceRange) { this.priceRange = priceRange; }

    public List<EatCategories> getCategories() { return categories; }
    public void setCategories(List<EatCategories> categories) { this.categories = categories; }
}
