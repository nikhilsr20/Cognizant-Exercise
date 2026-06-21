DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON a.CustomerID = c.CustomerID
        JOIN Transactions t ON t.AccountID = a.AccountID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.CustomerID, t.TransactionDate;
BEGIN
    FOR statement_record IN GenerateMonthlyStatements LOOP
        DBMS_OUTPUT.PUT_LINE(statement_record.CustomerID || ' - ' || statement_record.Name || ' - ' || statement_record.TransactionType || ' - ' || statement_record.Amount || ' - ' || TO_CHAR(statement_record.TransactionDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
