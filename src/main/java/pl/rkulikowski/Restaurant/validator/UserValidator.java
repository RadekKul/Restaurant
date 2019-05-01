package pl.rkulikowski.Restaurant.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.rkulikowski.Restaurant.model.User;
import pl.rkulikowski.Restaurant.service.UserService;

@Component
public class UserValidator implements Validator {

    UserService userService;

    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(!user.getPasswordConfirm().equals(user.getPassword())){
            errors.rejectValue("password","registration.user.passwordConfirm");
        }

        if(userService.findByUsername(user.getUsername()) != null){
            errors.rejectValue("username", "registration.user.username");
        }

    }






}
