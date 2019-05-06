package pl.rkulikowski.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.rkulikowski.Restaurant.model.Item;
import pl.rkulikowski.Restaurant.model.Menu;
import pl.rkulikowski.Restaurant.model.Place;
import pl.rkulikowski.Restaurant.service.ItemService;
import pl.rkulikowski.Restaurant.service.MenuService;
import pl.rkulikowski.Restaurant.service.PlaceService;
import pl.rkulikowski.Restaurant.validator.ItemValidator;
import pl.rkulikowski.Restaurant.validator.MenuValidator;
import pl.rkulikowski.Restaurant.validator.PlaceValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ItemValidator itemValidator;
    private ItemService itemService;

    private MenuValidator menuValidator;
    private MenuService menuService;

    private PlaceValidator placeValidator;
    private PlaceService placeService;

    public AdminController(ItemValidator itemValidator, ItemService itemService, MenuValidator menuValidator, MenuService menuService, PlaceValidator placeValidator, PlaceService placeService) {
        this.itemValidator = itemValidator;
        this.itemService = itemService;
        this.menuValidator = menuValidator;
        this.menuService = menuService;
        this.placeValidator = placeValidator;
        this.placeService = placeService;
    }

    @RequestMapping(value = "/dataUpdate/addItem", method = RequestMethod.GET)
    public String addItem (Model model){
        model.addAttribute("item",new Item());
        model.addAttribute("menus", menuService.findAll());

        return "/admin/dataUpdate/addItem";
    }

    @RequestMapping(value = "/dataUpdate/addItem", method = RequestMethod.POST)
    public String addItem (@Valid @ModelAttribute("item") Item item, BindingResult bindingResult, Model model){

        itemValidator.validate(item,bindingResult);

        if (bindingResult.hasErrors()) {

            model.addAttribute("menus", menuService.findAll());

            return "/admin/dataUpdate/addItem";
        }

        itemService.save(item);

        return "redirect:/account/panelAdmin";
    }

    @RequestMapping(value = "/dataUpdate/addTable", method = RequestMethod.GET)
    public String addTable (Model model){

        model.addAttribute("table", new Place());

        return "/admin/dataUpdate/addTable";

    }

    @RequestMapping(value = "/dataUpdate/addTable", method = RequestMethod.POST)
    public String addTable (@Valid @ModelAttribute("table") Place table, BindingResult bindingResult){

        placeValidator.validate(table,bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/dataUpdate/addTable";
        }

        placeService.save(table);

        return "redirect:/account/panelAdmin";

    }

    @RequestMapping(value = "/dataUpdate/addMenu", method = RequestMethod.GET)
    public String addMenu (Model model){

        model.addAttribute("menu", new Menu());

        return "/admin/dataUpdate/addMenu";

    }

    @RequestMapping(value = "/dataUpdate/addMenu", method = RequestMethod.POST)
    public String addMenu (@Valid @ModelAttribute("menu") Menu menu, BindingResult bindingResult){

        menuValidator.validate(menu,bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/dataUpdate/addMenu";
        }

        menuService.save(menu);

        return "redirect:/account/panelAdmin";

    }




}
