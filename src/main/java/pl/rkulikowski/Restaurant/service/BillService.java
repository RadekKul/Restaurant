package pl.rkulikowski.Restaurant.service;

import pl.rkulikowski.Restaurant.model.Bill;

import java.util.List;

public interface BillService {

    Bill findById(long id);
    List<Bill> findAllByPlaceId(long id);
    void save(Bill bill);
}
