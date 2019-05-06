package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.Place;

import java.util.Set;

public interface PlaceService {

    Set<Place> findFreeTables();
    Place findByName(String name);
    void save(Place place);
}
