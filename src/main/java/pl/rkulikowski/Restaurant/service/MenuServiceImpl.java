package pl.rkulikowski.Restaurant.service;

import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.MenuRepository;
import pl.rkulikowski.Restaurant.model.Menu;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Menu findByType(String type) {
        return menuRepository.findByType(type);
    }
}
