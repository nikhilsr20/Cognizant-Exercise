BEGIN
    FOR due_loan IN (
        SELECT c.Name, l.LoanID, l.EndDate
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE) + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: ' || due_loan.Name || ', loan ' || due_loan.LoanID || ' is due on ' || TO_CHAR(due_loan.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
