package pl.rkulikowski.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rkulikowski.Restaurant.model.Item;
import pl.rkulikowski.Restaurant.service.ItemService;
import pl.rkulikowski.Restaurant.service.MenuService;
import pl.rkulikowski.Restaurant.service.PlaceService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private PlaceService placeService;
    private MenuService menuService;
    private ItemService itemService;


    // ten konstruktor bedzie rozszerzany zaleznie od tego co tutaj bedzie potrzbene


    public UserController(PlaceService placeService, MenuService menuService, ItemService itemService) {
        this.placeService = placeService;
        this.menuService = menuService;
        this.itemService = itemService;
    }



    //TODO: Zmienic tak controllery i front zeby dzialaly na AJAX i ladowaly tylko to co jest potrzebne
    //TODO: Pomyslec juz powoli o koszyku i o dodawaniu zamowien i zliczaniu
    //TODO: Przemyslec jak z frontem ma to wygladac dla uzytkownika
    @GetMapping("/booking")
    public String bookTable(Model model){

        model.addAttribute("tables",placeService.findFreeTables());

        model.addAttribute("menus",menuService.findAll());

        model.addAttribute("dishes",itemService.findAllByMenuId(menuService.findByType("Dania").getId()));
        model.addAttribute("drinks",itemService.findAllByMenuId(menuService.findByType("Napoje").getId()));
        model.addAttribute("pizzas", itemService.findAllByMenuId(menuService.findByType("Pizza").getId()));
        model.addAttribute("starters",itemService.findAllByMenuId(menuService.findByType("Przystawki").getId()));
        model.addAttribute("desserts",itemService.findAllByMenuId(menuService.findByType("Desery").getId()));

        return "/booking/bookTable";
    }



    @RequestMapping(value = {"ajax/bookTable/menuType/{menuId}"})
    public String getTypeOfMenu(Model model, @PathVariable long menuId) {
        List<Item> selectedItems = new ArrayList<>();
        model.addAttribute("selectedItems",selectedItems);
        model.addAttribute("items", itemService.findAllByMenuId(menuId));
        return "/booking/ajaxItemMenu";
    }




}
