CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance Accounts.Balance%TYPE;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Transfer amount must be positive');
    END IF;

    SELECT Balance
    INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20002, 'Insufficient funds');
    END IF;

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (TransactionSeq.NEXTVAL, p_from_account_id, SYSDATE, p_amount, 'Withdrawal');

    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES (TransactionSeq.NEXTVAL, p_to_account_id, SYSDATE, p_amount, 'Deposit');

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Destination account does not exist');
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ModuleName, ErrorMessage, ErrorDate)
        VALUES (ErrorLogSeq.NEXTVAL, 'SafeTransferFunds', SQLERRM, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(SQLERRM);
END;
/
