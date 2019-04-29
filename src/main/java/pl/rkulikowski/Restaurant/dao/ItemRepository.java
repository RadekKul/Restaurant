package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rkulikowski.Restaurant.model.Item;

public interface ItemRepository extends JpaRepository<Item,Long> {
}
