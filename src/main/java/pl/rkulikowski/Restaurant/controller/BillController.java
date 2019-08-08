package pl.rkulikowski.Restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rkulikowski.Restaurant.model.Bill;
import pl.rkulikowski.Restaurant.model.Item;
import pl.rkulikowski.Restaurant.service.BillService;
import pl.rkulikowski.Restaurant.service.ItemService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class BillController {

    private BillService billService;
    private ItemService itemService;

    public BillController(BillService billService, ItemService itemService) {
        this.billService = billService;
        this.itemService = itemService;
    }

    private static final String AJAX_HEADER_NAME = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    @RequestMapping(path = {"/order", "/order/{id}"} , method = RequestMethod.GET)
    public String showOrder(@PathVariable(required = false) Long id, Model model) {
        Bill bill = billService.findById(id);
        model.addAttribute(bill);
        return "";
    }

    // Request is accepted by the endpoint only if contains "save" parameter.
    @RequestMapping(params = "save", path = {"/order", "/order/{id}"}, method = RequestMethod.POST)
    public String saveOrder(@PathVariable(required = false) Long id, Bill bill) {
        billService.save(bill);
        return "";
    }

    @RequestMapping(params = "addItem", path = {"/order", "/order/{id}"}, method = RequestMethod.POST)
    public String addOrder(@PathVariable(required = false) Long id, Bill bill, Item item, HttpServletRequest request) {
        bill.getItems().add(item);
//        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
//            // It is an Ajax request, render only #items fragment of the page.
//            return "order::#items";
//            // It is a standard HTTP request, render whole page.
//            return "order";
//        }
        return "";
    }

    // "removeItem" parameter contains index of a item that will be removed.
    @RequestMapping(params = "removeItem", path = {"/order", "/order/{id}"}, method = RequestMethod.POST)
    public String removeOrder(@PathVariable(required = false) Long id, Bill bill, Item item, @RequestParam("removeItem") int index, HttpServletRequest request) {
        bill.getItems().remove(item);
//        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
//            return "order::#items";
//        } else {
//            return "order";
//        }
        return "";
    }


}
