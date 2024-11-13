package BankManagementSystem;

import java.util.Date;

public class Transaction {
    private static int transactionCounter = 0;
    private int transactionId;
    private Date date;
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.transactionId = ++transactionCounter;
        this.date = new Date();
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ID: " + transactionId + ", Date: " + date + ", Type: " + type + ", Amount: " + amount;
    }
}
