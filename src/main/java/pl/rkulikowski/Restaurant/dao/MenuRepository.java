package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.Menu;

import java.util.List;
import java.util.Set;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

    List<Menu> findAll();
    Menu findByType(String type);
}
