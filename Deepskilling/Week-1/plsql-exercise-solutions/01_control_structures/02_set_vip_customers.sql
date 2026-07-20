BEGIN
    FOR customer_record IN (
        SELECT CustomerID
        FROM Customers
        WHERE Balance > 10000
    ) LOOP
        UPDATE Customers
        SET IsVIP = 'Y'
        WHERE CustomerID = customer_record.CustomerID;
    END LOOP;

    COMMIT;
END;
/
