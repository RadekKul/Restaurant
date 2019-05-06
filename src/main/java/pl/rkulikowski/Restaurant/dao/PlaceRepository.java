package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.Place;

import java.util.Set;

@Repository
public interface PlaceRepository extends JpaRepository<Place,Long> {

    Set<Place> findAllByReserved(boolean reserved);
    Place findByName(String name);
}
