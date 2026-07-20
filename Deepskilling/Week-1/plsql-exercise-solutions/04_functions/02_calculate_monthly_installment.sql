CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount IN NUMBER,
    p_interest_rate IN NUMBER,
    p_duration_years IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate NUMBER;
    v_months NUMBER;
BEGIN
    v_monthly_rate := p_interest_rate / 100 / 12;
    v_months := p_duration_years * 12;

    IF v_months <= 0 THEN
        RAISE_APPLICATION_ERROR(-20201, 'Loan duration must be positive');
    END IF;

    IF v_monthly_rate = 0 THEN
        RETURN ROUND(p_loan_amount / v_months, 2);
    END IF;

    RETURN ROUND((p_loan_amount * v_monthly_rate * POWER(1 + v_monthly_rate, v_months)) / (POWER(1 + v_monthly_rate, v_months) - 1), 2);
END;
/
