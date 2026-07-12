package com.cognizant.billingservice.service;

import com.cognizant.billingservice.entity.Bill;

import java.util.List;

public interface BillingService {
    Bill createBill(Bill bill);
    List<Bill> getAllBills();
    Bill getBillById(Long id);
    List<Bill> getBillsByCustomerId(Long customerId);
}
