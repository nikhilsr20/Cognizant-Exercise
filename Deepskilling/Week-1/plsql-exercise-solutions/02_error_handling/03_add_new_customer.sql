CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, IsVIP, LastModified)
    VALUES (p_customer_id, p_name, p_dob, p_balance, CASE WHEN p_balance > 10000 THEN 'Y' ELSE 'N' END, SYSDATE);

    COMMIT;
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ModuleName, ErrorMessage, ErrorDate)
        VALUES (ErrorLogSeq.NEXTVAL, 'AddNewCustomer', 'Customer ID already exists: ' || p_customer_id, SYSDATE);
        COMMIT;
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ModuleName, ErrorMessage, ErrorDate)
        VALUES (ErrorLogSeq.NEXTVAL, 'AddNewCustomer', SQLERRM, SYSDATE);
        COMMIT;
END;
/
