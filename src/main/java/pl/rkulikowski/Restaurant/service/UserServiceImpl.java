package pl.rkulikowski.Restaurant.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.RoleRepository;
import pl.rkulikowski.Restaurant.dao.UserRepository;
import pl.rkulikowski.Restaurant.model.Role;
import pl.rkulikowski.Restaurant.model.User;

import java.util.*;

@Service    //   jak komponent tylko inna nazwa zeby bylo wiadomo
public class UserServiceImpl implements UserService {   // to jest klasa ktora potem uzyje sie w Controlerze np do tworzenia uzytkownika, implementuje UserService z tymi metodami.
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;


    //konstruktor do wstrzykiwania zaleznosci  - zamiast autowired
    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(User user) {

        try {

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            Role roleOfUser = roleRepository.findRoleByName("ROLE_USER");
            user.setRoles(new HashSet<>(Collections.singletonList(roleOfUser)));
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        //user.setRoles(new HashSet<>(roleRepository.findAll()));
        //user.setRoles(roleUserSet);
        user.setEnabled(true); // sprawdzic czy to dziala czy w mysql jest ok

        userRepository.save(user);
    }

    // te dwie metody moga sie przydac pozniej w modyfikowaniu uzytkownikow itd.

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getPrincipal() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
