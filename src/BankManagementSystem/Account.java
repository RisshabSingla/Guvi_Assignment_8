package BankManagementSystem;

import java.util.ArrayList;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account(String accountNumber, String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        this.transactions.add(new Transaction("Account Opening Deposit", initialDeposit));
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction("deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactions.add(new Transaction("withdrawal", amount));
            System.out.println("Withdrawal successful!");
        } else {
            transactions.add(new Transaction("Unsuccessful Withdrawal", amount));
            System.out.println("Insufficient funds!");
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}

