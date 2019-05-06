package pl.rkulikowski.Restaurant.service;

import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.PlaceRepository;
import pl.rkulikowski.Restaurant.model.Place;

import java.util.Set;

@Service
public class PlaceServiceImpl implements PlaceService{

    private PlaceRepository placeRepository;


    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public Set<Place> findFreeTables() {
        return placeRepository.findAllByReserved(false); // wywolanie przez service tego co jest w dao z wartoscia false czyli wsyzstkie wolne stoliki
    }

    @Override
    public Place findByName(String name) {
        return placeRepository.findByName(name);
    }

    @Override
    public void save(Place place) {
        placeRepository.save(place);
    }
}
