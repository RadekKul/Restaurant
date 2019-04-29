package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
