package edu.esiea.examandroid.data.mapper;

import java.util.List;

import edu.esiea.examandroid.data.dto.PlaceWithDetails;
import edu.esiea.examandroid.data.entity.CulturalPlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceEntity;
import edu.esiea.examandroid.data.entity.PlaceToEatEntity;
import edu.esiea.examandroid.data.entity.PlaceToExerciseEntity;
import edu.esiea.examandroid.data.entity.PlaceToGoOutEntity;
import edu.esiea.examandroid.data.entity.PlaceToRelaxEntity;
import edu.esiea.examandroid.data.entity.PlaceToSleepEntity;
import edu.esiea.examandroid.enums.CulturalCategories;
import edu.esiea.examandroid.enums.EatCategories;
import edu.esiea.examandroid.enums.GoOutCategories;
import edu.esiea.examandroid.enums.RelaxCategories;
import edu.esiea.examandroid.enums.SleepCategories;
import edu.esiea.examandroid.enums.PlaceType;
import edu.esiea.examandroid.enums.PriceRange;
import edu.esiea.examandroid.enums.SportCategories;
import edu.esiea.examandroid.model.CulturalPlace;
import edu.esiea.examandroid.model.Place;
import edu.esiea.examandroid.model.PlaceToEat;
import edu.esiea.examandroid.model.PlaceToExercise;
import edu.esiea.examandroid.model.PlaceToRelax;
import edu.esiea.examandroid.model.PlaceToSleep;
import edu.esiea.examandroid.model.PlaceToGoOut;


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
                return new PlaceToGoOut(id,name,description, phoneNumber, email, website, latitude, longitude, type, openingHours, entryFee, goOutCategories);

            case PlaceToRelax:

                PlaceToRelaxEntity relaxDetail = dto.getPlaceToRelaxEntity();
                if (relaxDetail == null){
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                String relaxOpeningHours = relaxDetail.getOpeningHours();
                double relaxEntryFee = relaxDetail.getEntryFee();
                List<RelaxCategories> relaxCategories= relaxDetail.getCategories();
                return new PlaceToRelax(id,name,description, phoneNumber, email, website, latitude, longitude, type, relaxOpeningHours, relaxEntryFee, relaxCategories);

            case PlaceToExercise:

                PlaceToExerciseEntity sportDetail = dto.getPlaceToExerciseEntity();
                if (sportDetail == null){
                return new Place(id, name, description, phoneNumber, email, website,
                        latitude, longitude, type);
                }
                String sportOpeningHours = sportDetail.getOpeningHours();
                double sportEntryFee = sportDetail.getEntryFee();
                List<SportCategories> sportCategories = sportDetail.getCategories();
                boolean subscription = sportDetail.getMandatorySubscription();
                return new PlaceToExercise(id,name,description, phoneNumber, email, website, latitude, longitude, type, sportOpeningHours, sportCategories, sportEntryFee, subscription);

            case CulturalPlace:

                CulturalPlaceEntity culturalDetail = dto.getCulturalPlaceEntity();
                if (culturalDetail == null){
                    return new Place(id, name, description, phoneNumber, email, website,
                            latitude, longitude, type);
                }
                String culturalOpeningHours = culturalDetail.getOpeningHours();
                double culturalEntryFee = culturalDetail.getEntryFee();
                List<CulturalCategories> culturalCategories = culturalDetail.getCategories();
                return new CulturalPlace(id,name,description, phoneNumber, email, website, latitude, longitude, type, culturalOpeningHours, culturalEntryFee, culturalCategories);

            default:
                return new Place(id, name, description, phoneNumber, email, website,
                        latitude, longitude, type);
        }
    }
}
