CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance Accounts.Balance%TYPE;
BEGIN
    IF p_amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20101, 'Transfer amount must be positive');
    END IF;

    SELECT Balance
    INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20102, 'Insufficient balance');
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
        RAISE_APPLICATION_ERROR(-20103, 'Destination account not found');
    END IF;

    COMMIT;
END;
/
