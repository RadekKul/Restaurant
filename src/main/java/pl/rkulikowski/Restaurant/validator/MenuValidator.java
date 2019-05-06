package pl.rkulikowski.Restaurant.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.rkulikowski.Restaurant.model.Menu;
import pl.rkulikowski.Restaurant.service.MenuService;

@Component
public class MenuValidator implements Validator {


    MenuService menuService;

    public MenuValidator(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Menu.class.equals(aClass);
    }

    //TODO: nadpisac defaultowy blad przy wpisaniu czego innego niz float w validatorach

    @Override
    public void validate(Object o, Errors errors) {
        Menu menu = (Menu) o;

        if (menuService.findByType(menu.getType()) != null) {
            errors.rejectValue("type", "registration.menu.type");
        }
    }
}
