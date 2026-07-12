package com.cognizant.billingservice.service;

import com.cognizant.billingservice.entity.Bill;
import com.cognizant.billingservice.exception.BillNotFoundException;
import com.cognizant.billingservice.repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

    private final BillRepository billRepository;

    @Override
    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepository.findById(id)
                .orElseThrow(() -> new BillNotFoundException(id));
    }

    @Override
    public List<Bill> getBillsByCustomerId(Long customerId) {
        return billRepository.findByCustomerId(customerId);
    }
}
