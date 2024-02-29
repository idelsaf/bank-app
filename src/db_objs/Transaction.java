package db_objs;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private final int userId;
    private final String transactionType;
    private final Date transactionDate;
    private final BigDecimal transactionAmount;

    public Transaction(int userId, String transactionType, Date transactionDate, BigDecimal transactionAmount) {
        this.userId = userId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
    }

    public int getUserId() {
        return userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }
}
