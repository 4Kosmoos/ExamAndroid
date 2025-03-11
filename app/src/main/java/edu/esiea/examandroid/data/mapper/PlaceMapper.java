package edu.esiea.examandroid.data.mapper;

import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.GoOutCategories;
import edu.esiea.examandroid.enums.SleepCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.model.Place;
import edu.esiea.examandroid.model.PlaceToEat;
import edu.esiea.examandroid.model.PlaceToSleep;
import edu.esiea.examandroid.model.placeToGoOut;


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
                PlaceToEatEntity eatDetail = dto.getPlaceToEatEntity();
                if (eatDetail == null) {
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                PriceRange priceRange = eatDetail.getPriceRange();
                List<EatCategories> categories = eatDetail.getCategories();
                return new PlaceToEat(id,name,description, phoneNumber, email, website, latitude, longitude, type, priceRange, categories
                );

            case PlaceToSleep:
                PlaceToSleepEntity sleepDetail = dto.getPlaceToSleepEntity();
                if (sleepDetail == null) {
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                double minNightPrice = sleepDetail.getMinNightPrice();
                List<SleepCategories> sleepCategories = sleepDetail.getCategories();
                return new PlaceToSleep(id,name,description, phoneNumber, email, website, latitude, longitude, type, sleepCategories, minNightPrice
                );

            case PlaceToGoOut:

                PlaceToGoOutEntity goOutDetail = dto.getPlaceToGoOutEntity();
                if (goOutDetail == null){
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                String openingHours = goOutDetail.getOpeningHours();
                double entryFee = goOutDetail.getEntryFee();
                List<GoOutCategories> goOutCategories = goOutDetail.getCategories();
                return new placeToGoOut(id,name,description, phoneNumber, email, website, latitude, longitude, type, openingHours, entryFee, goOutCategories);

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
