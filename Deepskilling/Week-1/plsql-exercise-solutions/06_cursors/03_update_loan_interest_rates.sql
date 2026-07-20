DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount
        FROM Loans
        FOR UPDATE;
BEGIN
    FOR loan_record IN UpdateLoanInterestRates LOOP
        UPDATE Loans
        SET InterestRate =
            CASE
                WHEN loan_record.LoanAmount >= 100000 THEN 8
                WHEN loan_record.LoanAmount >= 50000 THEN 7
                ELSE 6
            END
        WHERE CURRENT OF UpdateLoanInterestRates;
    END LOOP;

    COMMIT;
END;
/
