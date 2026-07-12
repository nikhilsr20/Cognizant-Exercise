package com.cognizant.springbootdemo;

import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SonarIssueExamplesTest {

    @Test
    void createsPurchaseReferenceWithSecureRandomNumber() {
        SecureRandom secureRandom = mock(SecureRandom.class);
        when(secureRandom.nextInt(900000)).thenReturn(456789);
        SonarIssueExamples examples = new SonarIssueExamples("Rishabh Dubey", secureRandom);

        String reference = examples.createPurchaseReference();

        assertThat(reference).isEqualTo("CAR-556789");
    }

    @Test
    void formatsCarModelWithTrimmedValue() {
        SonarIssueExamples examples = new SonarIssueExamples();

        String message = examples.formatCarModel(" Honda City ZX ");

        assertThat(message).isEqualTo("Rishabh Dubey wants to purchase Honda City ZX");
    }

    @Test
    void usesDefaultTextWhenCarModelIsBlank() {
        SonarIssueExamples examples = new SonarIssueExamples();

        String message = examples.formatCarModel(" ");

        assertThat(message).isEqualTo("Rishabh Dubey wants to purchase a new car");
    }

    @Test
    void usesDefaultTextWhenCarModelIsNull() {
        SonarIssueExamples examples = new SonarIssueExamples();

        String message = examples.formatCarModel(null);

        assertThat(message).isEqualTo("Rishabh Dubey wants to purchase a new car");
    }

    @Test
    void identifiesPremiumCar() {
        SonarIssueExamples examples = new SonarIssueExamples();

        assertThat(examples.isPremiumCar("Honda City ZX")).isTrue();
        assertThat(examples.isPremiumCar("Maruti Swift")).isFalse();
    }

    @Test
    void createsBudgetMessage() {
        SonarIssueExamples examples = new SonarIssueExamples();

        assertThat(examples.createBudgetMessage(1500000))
                .isEqualTo("Rishabh Dubey has a car budget of 1500000");
    }

    @Test
    void createsBudgetMessageWhenBudgetIsMissing() {
        SonarIssueExamples examples = new SonarIssueExamples();

        assertThat(examples.createBudgetMessage(0))
                .isEqualTo("Rishabh Dubey has not set a car budget");
    }
}
