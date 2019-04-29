package pl.rkulikowski.Restaurant.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.rkulikowski.Restaurant.model.Role;
import pl.rkulikowski.Restaurant.model.User;
import pl.rkulikowski.Restaurant.service.UserService;
import pl.rkulikowski.Restaurant.utils.SecurityConst;
import pl.rkulikowski.Restaurant.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

//TODO: porobic widoki stron zeby to juz jakos wygladalo
//TODO: panel admina - struktura + przyciski ktore przenosza w dane miejsce


//TODO: https://bootsnipp.com/snippets/X04B0 , z tej strony sa pobrane widoki zeby nie bawic sie w css i js

//TODO: pamietac o zmianie nazwanych pól w szablonie form.js i zmienic je na nazwy swoich pol z ktoryhc bedziesz
// korzystal w login/registration

// bootstrap - framework do css, mozna automatycznie oscylowac w latwiejszy sposob niz pisac te podstawowe metody
//jquery - framework do js, latwiejszy sposob do dynamicznych zmian na stronie


@Controller
public class MainController {

    private UserService userService;    // robimy sobie implementacje user serbice, mamy tam metode z authentication, dopisujemy getprincipal i mamy obiekt ktory jest obecnie zalogowany
    private UserValidator userValidator;

    public MainController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

        // Wstrzyknac trzeba zaleznosc bcrypt do service i zakodowac haslo i potem userRepository save()
        //UserController ma odebrac obiekt usera i przekazad do UserService zeby zakodowal haslo i zapisal Usera do bazy
        // metoda kontrolera musi przyjmowac model czyli np reigster(Model model) i potem model.addAttribute("user",new User()), a w widoku na formularzu trzeba zmapowac tego usera do pol
        // i potem sam bedzie mi juz przypisywal to co jest wpisane , Potem tworzy sie metode post i odebrac juz wypelniony model usera i przekazac do service zeby zakodowal haslo i wypchnal do bazy
        // hibernatem jeszcze mozna wprowadzic po drodze validacje, poprawne haslo, dlugosc itp
        // zrobic ten model tu w Controlerze albo w UserController i nowy widok registration i tam sprobowac stworzyc nowego usera

    @RequestMapping(value = "/login/form", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)  // zle logowanie
            model.addAttribute("loginError", true);

        if (logout != null)
            model.addAttribute("logoutMessage", true);

        //TODO: zrobic validator, jest zrobiony na username i confirmpassword

// dopiero tutaj nas przenosi na widok login, gdzie wpisujemy dane(a tak dokladnie to tylko wyswietla ten widok)
        return "account/login";
    }

    @RequestMapping(value = {"/account/loginPage"}, method = RequestMethod.GET)
    public String loginPage(Model model) {

        model.addAttribute("name", userService.getPrincipal().getFirstName());

        boolean isAdmin = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(authority -> authority.equals(SecurityConst.ADMIN_ROLE_NAME));

        if (isAdmin) {
            System.out.println("Zalogowano poprawnie, przeniesiono do panelu administratora");
            return "redirect:/account/panelAdmin";
        }

        return "redirect:/account/panelUser";
    }

    @RequestMapping(value = {"/account/panelAdmin"}, method = RequestMethod.GET)
    public String panelAdmin(Model model) {
        model.addAttribute("name", userService.getPrincipal().getFirstName());
        return "/account/panelAdmin";
    }

    @RequestMapping(value = {"/account/panelUser"}, method = RequestMethod.GET)
    public String panelUser(Model model) {
        model.addAttribute("name", userService.getPrincipal().getFirstName());
        return "/account/panelUser";
    }

    // co za roznica jest tutaj w get i w post, czy ma znaczenie co potem wpisalem na widoku?

    // stworzylo usera ale nadalo mu prawa i admin i user pewnie przez findall() w userService
    // naprawic to ze user z prawami usera moze wejsc do paneluAdmina
    // dlaczego jak przekierowuje do /login po logowaniu to dostaje error? a np w panelAdmin wszystko dziala
    //

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (Model model){
        model.addAttribute("user",new User());

        return "/account/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration (@Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        userValidator.validate(user,bindingResult);

        if (bindingResult.hasErrors()) {
            return "/account/registration";
        }
        userService.save(user);

        return "redirect:/account/loginPage";
    }


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String homePage() {
        return "index";
    }


    @RequestMapping(value = {"/welcome"}, method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }


    @GetMapping(value = "/accessDenied")
    public String accessDenied() {
        return "/errors/forbidden-403";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}