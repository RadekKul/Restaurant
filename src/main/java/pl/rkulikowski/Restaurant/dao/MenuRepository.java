package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rkulikowski.Restaurant.model.Menu;

public interface MenuRepository extends JpaRepository<Menu,Long> {
}
