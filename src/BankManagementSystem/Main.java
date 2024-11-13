package BankManagementSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    bank.register(username, password);
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine();

                    int type = bank.login(username, password);
                    if(type == 1) {
                        adminMenu(bank, scanner);
                    }else if(type == 2) {
                        loggedInMenu(bank, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    private static void loggedInMenu(Bank bank, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Show Statement");
            System.out.println("6. Logout");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter account type (savings/checking): ");
                    String type = scanner.nextLine();

                    double initialDeposit = 0.0;
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("Enter initial deposit: ");
                        try {
                            initialDeposit = scanner.nextDouble();
                            if (initialDeposit < 0) {
                                System.out.println("Deposit amount cannot be negative. Please try again.");
                            } else {
                                validInput = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            scanner.nextLine();
                        }
                    }

                    bank.openAccount(name, type, initialDeposit);
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.next();
                    double amount = 0.0;
                    validInput = false;

                    while (!validInput) {
                        System.out.print("Enter amount to deposit: ");
                        try {
                            amount = scanner.nextDouble();
                            if (amount < 0) {
                                System.out.println("Deposit amount cannot be negative. Please try again.");
                            } else {
                                validInput = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            scanner.nextLine();
                        }
                    }

                    bank.deposit(accNumber, amount);
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accNumber = scanner.next();
                    double withdrawalAmount = 0.0;
                    validInput = false;

                    while (!validInput) {
                        System.out.print("Enter amount to withdraw: ");
                        try {
                            withdrawalAmount = scanner.nextDouble();
                            if (withdrawalAmount < 0) {
                                System.out.println("Withdrawal amount cannot be negative. Please try again.");
                            } else {
                                validInput = true;
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a numeric value.");
                            scanner.nextLine();
                        }
                    }

                    bank.withdraw(accNumber, withdrawalAmount);
                    break;

                case 4:
                    bank.showAllBalances();
                    break;

                case 5:
                    System.out.print("Enter account number: ");
                    accNumber = scanner.next();
                    bank.showStatement(accNumber);
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }


    private static void adminMenu(Bank bank, Scanner scanner) {
        int choice;
        do {
            System.out.println("\n1. View All Accounts");
            System.out.println("2. Logout");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bank.displayAllAccounts();
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }
}
