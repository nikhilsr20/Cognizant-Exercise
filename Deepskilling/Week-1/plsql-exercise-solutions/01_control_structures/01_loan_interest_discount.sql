BEGIN
    FOR customer_loan IN (
        SELECT l.LoanID, l.InterestRate
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE TRUNC(MONTHS_BETWEEN(SYSDATE, c.DOB) / 12) > 60
    ) LOOP
        UPDATE Loans
        SET InterestRate = GREATEST(customer_loan.InterestRate - 1, 0)
        WHERE LoanID = customer_loan.LoanID;
    END LOOP;

    COMMIT;
END;
/
