package com.cognizant.springbootdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;

class CarPurchaseServiceTest {

    private final CarPurchaseService carPurchaseService = new CarPurchaseService();

    @Nested
    @DisplayName("payment calculations")
    class PaymentCalculations {

        @ParameterizedTest
        @CsvSource({
                "1200000, 10, 120000",
                "1500000, 20, 300000",
                "900000, 0, 0",
                "2000000, 100, 2000000"
        })
        void calculatesDownPaymentForDifferentPercentages(int carPrice, int percentage, int expected) {
            int downPayment = carPurchaseService.calculateDownPayment(carPrice, percentage);

            assertThat(downPayment).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({
                "1500000, 300000, 1200000",
                "900000, 1000000, 0",
                "1000000, 0, 1000000"
        })
        void calculatesLoanAmountAfterDownPayment(int carPrice, int downPayment, int expected) {
            int loanAmount = carPurchaseService.calculateLoanAmount(carPrice, downPayment);

            assertThat(loanAmount).isEqualTo(expected);
        }

        @Test
        void calculatesEmiWithInterest() {
            double emi = carPurchaseService.calculateMonthlyEmi(1200000, 9.5, 60);

            assertThat(emi).isCloseTo(25202.23, within(0.50));
        }

        @Test
        void calculatesEmiWithoutInterest() {
            double emi = carPurchaseService.calculateMonthlyEmi(600000, 0, 24);

            assertThat(emi).isEqualTo(25000.0);
        }
    }

    @Nested
    @DisplayName("purchase decisions")
    class PurchaseDecisions {

        @ParameterizedTest
        @CsvSource({
                "1600000, 790, 1500000, true",
                "1300000, 790, 1500000, false",
                "1600000, 710, 1500000, false"
        })
        void checksPremiumCarEligibility(int budget, int creditScore, int carPrice, boolean expected) {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", budget, creditScore);

            boolean eligible = carPurchaseService.isEligibleForPremiumCar(profile, carPrice);

            assertThat(eligible).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource({
                "1700000, 760, 1500000, Full payment",
                "1200000, 720, 1500000, Loan",
                "1200000, 640, 1500000, Increase budget"
        })
        void recommendsPurchaseType(int budget, int creditScore, int carPrice, String expected) {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", budget, creditScore);

            String recommendation = carPurchaseService.recommendPurchaseType(profile, carPrice);

            assertThat(recommendation).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("validation")
    class Validation {

        @Test
        void rejectsInvalidDownPaymentPercentage() {
            assertThatThrownBy(() -> carPurchaseService.calculateDownPayment(1500000, 125))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Down payment percentage must be between 0 and 100");
        }

        @Test
        void rejectsInvalidCarPriceForDownPayment() {
            assertThatThrownBy(() -> carPurchaseService.calculateDownPayment(0, 20))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Car price must be greater than zero");
        }

        @Test
        void rejectsNegativeDownPayment() {
            assertThatThrownBy(() -> carPurchaseService.calculateLoanAmount(1500000, -1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Down payment cannot be negative");
        }

        @Test
        void rejectsInvalidCarPriceForLoanAmount() {
            assertThatThrownBy(() -> carPurchaseService.calculateLoanAmount(0, 100000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Car price must be greater than zero");
        }

        @Test
        void rejectsInvalidLoanAmountForEmi() {
            assertThatThrownBy(() -> carPurchaseService.calculateMonthlyEmi(0, 9.5, 60))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Loan amount must be greater than zero");
        }

        @Test
        void rejectsNegativeInterestRateForEmi() {
            assertThatThrownBy(() -> carPurchaseService.calculateMonthlyEmi(1200000, -1, 60))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Annual interest rate cannot be negative");
        }

        @Test
        void rejectsInvalidTenureForEmi() {
            assertThatThrownBy(() -> carPurchaseService.calculateMonthlyEmi(1200000, 9.5, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Tenure must be greater than zero");
        }

        @Test
        void rejectsInvalidCreditScore() {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", 1500000, 950);

            assertThatThrownBy(() -> carPurchaseService.createPurchaseSummary(profile))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Credit score must be between 300 and 900");
        }

        @Test
        void rejectsBlankCarModel() {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", " ", 1500000, 760);

            assertThatThrownBy(() -> carPurchaseService.recommendPurchaseType(profile, 1500000))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Car model is required");
        }

        @Test
        void rejectsNullProfile() {
            assertThatThrownBy(() -> carPurchaseService.createPurchaseSummary(null))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Profile is required");
        }

        @Test
        void rejectsBlankBuyerName() {
            CarPurchaseProfile profile = new CarPurchaseProfile(" ", "Honda City ZX", 1500000, 760);

            assertThatThrownBy(() -> carPurchaseService.createPurchaseSummary(profile))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Buyer name is required");
        }

        @Test
        void rejectsNegativeBudget() {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", -1, 760);

            assertThatThrownBy(() -> carPurchaseService.createPurchaseSummary(profile))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Budget cannot be negative");
        }

        @Test
        void rejectsInvalidCarPriceForEligibility() {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", 1500000, 760);

            assertThatThrownBy(() -> carPurchaseService.isEligibleForPremiumCar(profile, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Car price must be greater than zero");
        }

        @Test
        void rejectsInvalidCarPriceForRecommendation() {
            CarPurchaseProfile profile = new CarPurchaseProfile("Rishabh Dubey", "Honda City ZX", 1500000, 760);

            assertThatThrownBy(() -> carPurchaseService.recommendPurchaseType(profile, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Car price must be greater than zero");
        }
    }
}
