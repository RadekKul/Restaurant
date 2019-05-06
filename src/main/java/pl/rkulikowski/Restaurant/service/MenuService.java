package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();
    void save(Menu menu);
    Menu findByType(String type);

}

