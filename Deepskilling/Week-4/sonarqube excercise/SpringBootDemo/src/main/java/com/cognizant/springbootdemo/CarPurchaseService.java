package com.cognizant.springbootdemo;

import org.springframework.stereotype.Service;

@Service
public class CarPurchaseService {

    public CarPurchaseProfile createDefaultProfile() {
        return new CarPurchaseProfile("Rishabh Dubey",
                "Honda City ZX",
                1500000,
                760);
    }

    public String createPurchaseSummary(CarPurchaseProfile profile) {
        validateProfile(profile);
        return profile.displayBuyerName() + " wants to purchase " + profile.carModel();
    }

    public int calculateDownPayment(int carPrice, int downPaymentPercentage) {
        if (carPrice <= 0) {
            throw new IllegalArgumentException("Car price must be greater than zero");
        }
        if (downPaymentPercentage < 0 || downPaymentPercentage > 100) {
            throw new IllegalArgumentException("Down payment percentage must be between 0 and 100");
        }
        return carPrice * downPaymentPercentage / 100;
    }

    public int calculateLoanAmount(int carPrice, int downPayment) {
        if (carPrice <= 0) {
            throw new IllegalArgumentException("Car price must be greater than zero");
        }
        if (downPayment < 0) {
            throw new IllegalArgumentException("Down payment cannot be negative");
        }
        if (downPayment > carPrice) {
            return 0;
        }
        return carPrice - downPayment;
    }

    public double calculateMonthlyEmi(int loanAmount, double annualInterestRate, int tenureInMonths) {
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero");
        }
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative");
        }
        if (tenureInMonths <= 0) {
            throw new IllegalArgumentException("Tenure must be greater than zero");
        }
        if (annualInterestRate == 0) {
            return (double) loanAmount / tenureInMonths;
        }
        double monthlyRate = annualInterestRate / 12 / 100;
        double factor = Math.pow(1 + monthlyRate, tenureInMonths);
        return loanAmount * monthlyRate * factor / (factor - 1);
    }

    public boolean isEligibleForPremiumCar(CarPurchaseProfile profile, int carPrice) {
        validateProfile(profile);
        if (carPrice <= 0) {
            throw new IllegalArgumentException("Car price must be greater than zero");
        }
        return profile.hasValidBudget() && profile.budget() >= carPrice && profile.creditScore() >= 750;
    }

    public String recommendPurchaseType(CarPurchaseProfile profile, int carPrice) {
        validateProfile(profile);
        if (carPrice <= 0) {
            throw new IllegalArgumentException("Car price must be greater than zero");
        }
        if (profile.budget() >= carPrice) {
            return "Full payment";
        }
        if (profile.creditScore() >= 700) {
            return "Loan";
        }
        return "Increase budget";
    }

    private void validateProfile(CarPurchaseProfile profile) {
        if (profile == null) {
            throw new IllegalArgumentException("Profile is required");
        }
        if (profile.buyerName() == null || profile.buyerName().isBlank()) {
            throw new IllegalArgumentException("Buyer name is required");
        }
        if (profile.carModel() == null || profile.carModel().isBlank()) {
            throw new IllegalArgumentException("Car model is required");
        }
        if (profile.budget() < 0) {
            throw new IllegalArgumentException("Budget cannot be negative");
        }
        if (profile.creditScore() < 300 || profile.creditScore() > 900) {
            throw new IllegalArgumentException("Credit score must be between 300 and 900");
        }
    }
}
