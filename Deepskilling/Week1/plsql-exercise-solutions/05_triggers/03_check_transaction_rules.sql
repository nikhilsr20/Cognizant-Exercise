CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance Accounts.Balance%TYPE;
BEGIN
    IF :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20301, 'Transaction amount must be positive');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance
        INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20302, 'Withdrawal amount exceeds account balance');
        END IF;
    ELSIF :NEW.TransactionType <> 'Deposit' THEN
        RAISE_APPLICATION_ERROR(-20303, 'Transaction type must be Deposit or Withdrawal');
    END IF;
END;
/
