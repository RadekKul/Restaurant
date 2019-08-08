package pl.rkulikowski.Restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rkulikowski.Restaurant.model.Bill;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {

    Bill findById(long id);

    List<Bill> findAllByPlaceId(long id);

}
