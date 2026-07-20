CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer (
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    );

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    );

    FUNCTION GetCustomerBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer (
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    ) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP, LastModified)
        VALUES (p_customer_id, p_name, p_dob, p_balance, CASE WHEN p_balance > 10000 THEN 'Y' ELSE 'N' END, SYSDATE);
        COMMIT;
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails (
        p_customer_id IN NUMBER,
        p_name IN VARCHAR2,
        p_dob IN DATE,
        p_balance IN NUMBER
    ) AS
    BEGIN
        UPDATE Customers
        SET Name = p_name,
            DOB = p_dob,
            Balance = p_balance,
            IsVIP = CASE WHEN p_balance > 10000 THEN 'Y' ELSE 'N' END
        WHERE CustomerID = p_customer_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20401, 'Customer not found');
        END IF;

        COMMIT;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER AS
        v_balance Customers.Balance%TYPE;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID = p_customer_id;

        RETURN v_balance;
    END GetCustomerBalance;
END CustomerManagement;
/
