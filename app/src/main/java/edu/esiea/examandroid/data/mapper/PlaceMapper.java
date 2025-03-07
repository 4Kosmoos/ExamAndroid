package edu.esiea.examandroid.data.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.model.Place;
import edu.esiea.examandroid.model.PlaceToEat;


public class PlaceMapper {

    public static Place map(PlaceWithDetails dto) {
        PlaceEntity entity = dto.getPlace();
        PlaceType type = entity.getType();

        int id = entity.getId();
        String name = entity.getName();
        String description = entity.getDescription();
        String phoneNumber = entity.getPhoneNumber();
        String email = entity.getEmail();
        String website = entity.getWebsite();
        double latitude = entity.getLatitude();
        double longitude = entity.getLongitude();

        switch (type) {
            case PlaceToEat:
                PlaceToEatEntity eatDetail = dto.getPlaceToEat();
                if (eatDetail == null) {
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                PriceRange priceRange = eatDetail.getPriceRange();
                List<EatCategories> categories = eatDetail.getCategories();
                return new PlaceToEat(id,name,description, phoneNumber, email, website, latitude, longitude, type, priceRange, categories
                );

            case PlaceToSleep:

                break;

            case PlaceToGoOut:

                break;

            case PlaceToRelax:

                break;

            case PlaceToExercise:

                break;

            case CulturalPlace:

                break;

            default:
                return new Place(id, name, description, phoneNumber, email, website,
                        latitude, longitude, type);
        }
        return null;
    }
}
