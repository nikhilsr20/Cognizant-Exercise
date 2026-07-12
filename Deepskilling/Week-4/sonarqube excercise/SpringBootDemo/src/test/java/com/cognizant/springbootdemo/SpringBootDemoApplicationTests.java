package com.cognizant.springbootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringBootDemoApplicationTests {

    @Autowired
    private CarPurchaseService carPurchaseService;

    @Test
    void contextLoads() {
        assertThat(carPurchaseService).isNotNull();
    }

    @Test
    void createsDefaultCarPurchaseProfileForRishabhDubey() {
        CarPurchaseProfile profile = carPurchaseService.createDefaultProfile();

        assertThat(profile.buyerName()).isEqualTo("Rishabh Dubey");
        assertThat(profile.carModel()).isEqualTo("Honda City ZX");
        assertThat(profile.budget()).isEqualTo(1500000);
        assertThat(profile.creditScore()).isEqualTo(760);
    }

    @Test
    void createsPurchaseSummaryWithTrimmedBuyerName() {
        CarPurchaseProfile profile = new CarPurchaseProfile(" Rishabh Dubey ", "Hyundai Creta SX", 1800000, 780);

        String summary = carPurchaseService.createPurchaseSummary(profile);

        assertThat(summary).isEqualTo("Rishabh Dubey wants to purchase Hyundai Creta SX");
    }

    @Test
    void calculatesDownPayment() {
        int downPayment = carPurchaseService.calculateDownPayment(1500000, 20);

        assertThat(downPayment).isEqualTo(300000);
    }

    @Test
    void calculatesLoanAmount() {
        int loanAmount = carPurchaseService.calculateLoanAmount(1500000, 300000);

        assertThat(loanAmount).isEqualTo(1200000);
    }

    @Test
    void marksBuyerEligibleForPremiumCar() {
        CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", 1600000, 780);

        boolean eligible = carPurchaseService.isEligibleForPremiumCar(profile, 1500000);

        assertThat(eligible).isTrue();
    }

    @Test
    void recommendsLoanWhenBudgetIsLowerButCreditScoreIsGood() {
        CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", 1200000, 730);

        String recommendation = carPurchaseService.recommendPurchaseType(profile, 1500000);

        assertThat(recommendation).isEqualTo("Loan");
    }

}
