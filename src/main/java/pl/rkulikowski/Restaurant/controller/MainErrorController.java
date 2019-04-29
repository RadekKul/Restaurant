package pl.rkulikowski.Restaurant.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// contoller nadmieniajacy standardowa obsluge bledow

@Controller
public class MainErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "errors/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
