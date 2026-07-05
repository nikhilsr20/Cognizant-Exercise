CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount (
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance IN NUMBER
    );

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount (
        p_account_id IN NUMBER,
        p_customer_id IN NUMBER,
        p_account_type IN VARCHAR2,
        p_balance IN NUMBER
    ) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_account_type, p_balance, SYSDATE);
        COMMIT;
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    ) AS
    BEGIN
        DELETE FROM Transactions
        WHERE AccountID = p_account_id;

        DELETE FROM Accounts
        WHERE AccountID = p_account_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20601, 'Account not found');
        END IF;

        COMMIT;
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER AS
        v_total_balance NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0)
        INTO v_total_balance
        FROM Accounts
        WHERE CustomerID = p_customer_id;

        RETURN v_total_balance;
    END GetTotalBalance;
END AccountOperations;
/
