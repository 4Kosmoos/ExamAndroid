package edu.esiea.examandroid.data.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.model.Place;



public class PlaceMapper {

    public static Place map(PlaceWithDetails dto) {
        PlaceEntity entity = dto.getPlace();
        PlaceType type = entity.getType();

        // Champs communs à tous les types
        int id = entity.getId();
        String name = entity.getName();
        String description = entity.getDescription();
        String phone = entity.getPhoneNumber();
        String email = entity.getEmail();
        String website = entity.getWebsite();
        double latitude = entity.getLatitude();
        double longitude = entity.getLongitude();

        // On détermine le type de lieu pour mapper vers le modèle adéquat
        switch (type) {
            case PlaceToEat:
                PlaceToEatEntity eatDetail = dto.getPlaceToEat();

                String priceRange = (eatDetail != null) ? eatDetail.getPriceRange() : "";
                String categoriesStr = (eatDetail != null) ? eatDetail.getCategories() : "";
                List<String> categories = categoriesStr.isEmpty()
                        ? new ArrayList<>()
                        : Arrays.asList(categoriesStr.split(","));
                return new PlaceToEatEntity(id, priceRange, categories);

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
                return new Place(id, name, description, phone, email, website,
                        latitude, longitude, type);
        }
        return null;
    }
}
