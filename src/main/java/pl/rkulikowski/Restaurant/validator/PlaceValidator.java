package pl.rkulikowski.Restaurant.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.rkulikowski.Restaurant.model.Place;
import pl.rkulikowski.Restaurant.service.PlaceService;

@Component
public class PlaceValidator implements Validator {

    PlaceService placeService;

    public PlaceValidator(PlaceService placeService) {
        this.placeService = placeService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Place.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Place place = (Place) o;

        if (placeService.findByName(place.getName()) != null) {
            errors.rejectValue("name", "registration.place.name");
        }
    }
}