package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.Item;

import java.util.List;


public interface ItemService {

    Item findByName (String name);
    void save (Item item);
    List<Item> findAllByMenuId(long id);


}
