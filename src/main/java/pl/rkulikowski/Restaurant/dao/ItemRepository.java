package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    Item findByName(String name);
}
