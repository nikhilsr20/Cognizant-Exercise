CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (AuditID, TransactionID, AccountID, ActionDate, Details)
    VALUES (AuditLogSeq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, SYSDATE, 'Transaction inserted: ' || :NEW.TransactionType || ' amount ' || :NEW.Amount);
END;
/
