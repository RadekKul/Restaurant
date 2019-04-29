package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rkulikowski.Restaurant.model.Bill;

public interface BillRepository extends JpaRepository<Bill,Long> {

}
