package pl.rkulikowski.Restaurant.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.rkulikowski.Restaurant.model.Item;
import pl.rkulikowski.Restaurant.service.ItemService;

@Component
public class ItemValidator implements Validator {

    ItemService itemService;

    public ItemValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Item.class.equals(aClass);
    }


    @Override
    public void validate(Object o, Errors errors) {
        Item item = (Item) o;

        if (itemService.findByName(item.getName()) != null) {
            errors.rejectValue("name", "registration.item.name");
        }

        if (item.getPrice() == null) {
            errors.rejectValue("price","registration.item.price");
        }
    }
}
