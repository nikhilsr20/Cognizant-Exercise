package com.cognizant.springbootdemo;

public record CarPurchaseProfile(String buyerName, String carModel, int budget, int creditScore) {

    public String displayBuyerName() {
        return buyerName.strip();
    }

    public boolean hasValidBudget() {
        return budget > 0;
    }
}
