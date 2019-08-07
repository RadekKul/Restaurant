package pl.rkulikowski.Restaurant.service;

import org.springframework.stereotype.Service;
import pl.rkulikowski.Restaurant.dao.BillRepository;
import pl.rkulikowski.Restaurant.model.Bill;

import java.util.List;

@Service
public class BillServiceImpl implements  BillService {

    private BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Bill findById(long id) {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> findAllByPlaceId(long id) {
        return billRepository.findAllByPlaceId(id);
    }

    @Override
    public void save(Bill bill) {
        billRepository.save(bill);
    }
}
