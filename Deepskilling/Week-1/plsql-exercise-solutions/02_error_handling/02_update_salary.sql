CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ModuleName, ErrorMessage, ErrorDate)
        VALUES (ErrorLogSeq.NEXTVAL, 'UpdateSalary', 'Employee ID does not exist: ' || p_employee_id, SYSDATE);
        COMMIT;
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ErrorID, ModuleName, ErrorMessage, ErrorDate)
        VALUES (ErrorLogSeq.NEXTVAL, 'UpdateSalary', SQLERRM, SYSDATE);
        COMMIT;
END;
/
