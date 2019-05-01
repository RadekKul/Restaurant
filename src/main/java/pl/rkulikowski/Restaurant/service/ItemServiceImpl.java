package pl.rkulikowski.Restaurant.service;

import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.ItemRepository;
import pl.rkulikowski.Restaurant.model.Item;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }
}
