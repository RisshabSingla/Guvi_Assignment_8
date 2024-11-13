package BankManagementSystem;

import java.util.ArrayList;
import java.text.DecimalFormat;

public class Bank {
    private ArrayList<User> users = new ArrayList<>();
    private User loggedInUser;
    private static final String adminUsername = "admin";
    private static final String adminPassword = "admin123";

    private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public void register(String username, String password) {
        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    public int login(String username, String password) {
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            System.out.println("Admin login successful!");
            return 1;
        }
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful!");
                return 2;
            }
        }
        System.out.println("Invalid credentials!");
        return 0;
    }

    public void openAccount(String accountHolderName, String accountType, double initialDeposit) {
        String accountNumber = "ACC" + (loggedInUser.getAccounts().size() + 1);
        Account newAccount = new Account(accountNumber, accountHolderName, accountType, initialDeposit);
        loggedInUser.addAccount(newAccount);
        System.out.println("Account opened successfully with account number: " + accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful!");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        }
    }

    public void showAllBalances() {
        if (loggedInUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        System.out.println("Balances for all accounts:");
        for (Account account : loggedInUser.getAccounts()) {
            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + decimalFormat.format(account.getBalance()));
        }
    }

    public void showStatement(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Transaction History:");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        }
    }

    public void displayAllAccounts() {
        System.out.println("Displaying all user accounts:");
        for (User user : users) {
            System.out.println("User: " + user.getUsername());
            for (Account account : user.getAccounts()) {
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Account Holder: " + account.getAccountHolderName());
                System.out.println("Account Type: " + account.getAccountType());
                System.out.println("Balance: " + account.getBalance());
                System.out.println("Transactions:");
                for (Transaction transaction : account.getTransactions()) {
                    System.out.println("  " + transaction);
                }
                System.out.println();
            }
        }
    }

    private Account findAccount(String accountNumber) {
        for (Account account : loggedInUser.getAccounts()) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found!");
        return null;
    }
}
