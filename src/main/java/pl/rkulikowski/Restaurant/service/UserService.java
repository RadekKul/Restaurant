package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findById(Long id);
    User getPrincipal();
}
