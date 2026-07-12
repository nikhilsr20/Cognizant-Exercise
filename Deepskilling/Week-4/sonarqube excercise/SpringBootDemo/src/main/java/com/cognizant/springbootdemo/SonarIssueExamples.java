package com.cognizant.springbootdemo;

import java.security.SecureRandom;

public class SonarIssueExamples {

    private static final int MIN_REFERENCE_NUMBER = 100000;
    private static final int REFERENCE_NUMBER_RANGE = 900000;

    private final String buyerName;
    private final SecureRandom secureRandom;

    public SonarIssueExamples() {
        this("Rishabh Dubey", new SecureRandom());
    }

    SonarIssueExamples(String buyerName, SecureRandom secureRandom) {
        this.buyerName = buyerName;
        this.secureRandom = secureRandom;
    }

    public String createPurchaseReference() {
        int referenceNumber = MIN_REFERENCE_NUMBER + secureRandom.nextInt(REFERENCE_NUMBER_RANGE);
        return "CAR-" + referenceNumber;
    }

    public String formatCarModel(String carModel) {
        String selectedCarModel = carModel == null || carModel.isBlank() ? "a new car" : carModel.strip();
        return buyerName + " wants to purchase " + selectedCarModel;
    }

    public boolean isPremiumCar(String carModel) {
        return "Honda City ZX".equals(carModel);
    }

    public String createBudgetMessage(int budget) {
        if (budget <= 0) {
            return buyerName + " has not set a car budget";
        }
        return buyerName + " has a car budget of " + budget;
    }
}
