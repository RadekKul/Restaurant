package pl.rkulikowski.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rkulikowski.Restaurant.service.MenuService;
import pl.rkulikowski.Restaurant.service.PlaceService;


@Controller
@RequestMapping("/user")
public class UserController {

    private PlaceService placeService;
    private MenuService menuService;

    // ten konstruktor bedzie rozszerzany zaleznie od tego co tutaj bedzie potrzbene


    public UserController(PlaceService placeService, MenuService menuService) {
        this.placeService = placeService;
        this.menuService = menuService;
    }




    @GetMapping("/booking")
    public String bookTable(Model model){

        model.addAttribute("tables",placeService.findFreeTables());
        model.addAttribute("menus",menuService.findAll());

        return "/booking/bookTable";
    }


}
