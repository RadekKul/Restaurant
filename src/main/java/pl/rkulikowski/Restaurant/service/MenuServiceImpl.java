package pl.rkulikowski.Restaurant.service;

import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.MenuRepository;
import pl.rkulikowski.Restaurant.model.Menu;

import java.util.List;
import java.util.Set;

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
}
