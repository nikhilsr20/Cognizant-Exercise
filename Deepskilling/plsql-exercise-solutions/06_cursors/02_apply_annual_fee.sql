DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID
        FROM Accounts
        FOR UPDATE;
    v_fee NUMBER := 100;
BEGIN
    FOR account_record IN ApplyAnnualFee LOOP
        UPDATE Accounts
        SET Balance = GREATEST(Balance - v_fee, 0),
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;
    END LOOP;

    COMMIT;
END;
/
