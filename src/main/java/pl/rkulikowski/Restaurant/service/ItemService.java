package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.Item;

public interface ItemService {

    Item findByName (String name);
    void save (Item item);
}
