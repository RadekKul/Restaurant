package pl.rkulikowski.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.rkulikowski.Restaurant.model.Item;
import pl.rkulikowski.Restaurant.service.ItemService;
import pl.rkulikowski.Restaurant.service.MenuService;
import pl.rkulikowski.Restaurant.validator.ItemValidator;

import javax.validation.Valid;

@Controller
public class AdminController {


    private ItemValidator itemValidator;
    private ItemService itemService;
    private MenuService menuService;

    public AdminController(ItemValidator itemValidator, ItemService itemService, MenuService menuService) {
        this.itemValidator = itemValidator;
        this.itemService = itemService;
        this.menuService = menuService;
    }

    //TODO: poprawic tutaj zeby dawalo sie zapisac item

    @RequestMapping(value = "/admin/dataUpdate/addItem", method = RequestMethod.GET)
    public String addItem (Model model){

        model.addAttribute("item",new Item());
        model.addAttribute("menus", menuService.findAll());

        return "/admin/dataUpdate/addItem";
    }

    @RequestMapping(value = "/admin/dataUpdate/addItem", method = RequestMethod.POST)
    public String addItem (@Valid @ModelAttribute("item") Item item, BindingResult bindingResult){

        itemValidator.validate(item,bindingResult);

        if (bindingResult.hasErrors()) {
            return "/admin/dataUpdate/addItem";
        }
        itemService.save(item);

        return "redirect:/account/panelAdmin";
    }
}
