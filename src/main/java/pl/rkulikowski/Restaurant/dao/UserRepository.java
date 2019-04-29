package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.User;

import java.util.Optional;

@Repository // repozytorium jest tylko do generowania zapytan do bazy i do zwracania danych
public interface UserRepository extends JpaRepository<User, Long> { // to jest clasa ktora laczy nas z baza danych, jparepository to klasa ktora udostpenia mozliwosci prostszych zapytan (user- klasa do ktorej ma sie a long to typ id w tej klasie)
    User findByUsername(String username);
    Optional<User> findById(Long primaryKey);
    User findByEmail (String email);

}

