import java.util.HashMap;
import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully. New Balance: ₹" + balance);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully. New Balance: ₹" + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }

    // Check balance method
    public void checkBalance() {
        System.out.println("Current Balance: ₹" + balance);
    }

    // Get account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: ₹" + balance);
    }
}

class BankingSystem {
    private HashMap<String, BankAccount> accounts;

    // Constructor
    public BankingSystem() {
        accounts = new HashMap<>();
    }

    // Create a new account
    public void createAccount(String accountNumber, String accountHolder) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("An account with this number already exists!");
        } else {
            BankAccount newAccount = new BankAccount(accountNumber, accountHolder);
            accounts.put(accountNumber, newAccount);
            System.out.println("Account for " + accountHolder + " created successfully!");
        }
    }

    // Access an account
    public BankAccount accessAccount(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            return accounts.get(accountNumber);
        } else {
            System.out.println("Account not found!");
            return null;
        }
    }

    // Display all accounts
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the system.");
        } else {
            System.out.println("\n--- All Accounts ---");
            for (BankAccount account : accounts.values()) {
                account.displayAccountDetails();
                System.out.println("--------------------");
            }
        }
    }
}

public class BankingSystemMain {
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Access Account");
            System.out.println("3. Display All Accounts");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Account Holder Name: ");
                    String accountHolder = scanner.nextLine();
                    system.createAccount(accountNumber, accountHolder);
                    break;

                case 2:
                    System.out.print("Enter Account Number to Access: ");
                    accountNumber = scanner.nextLine();
                    BankAccount account = system.accessAccount(accountNumber);
                    if (account != null) {
                        while (true) {
                            System.out.println("\n--- Account Menu ---");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Exit to Main Menu");
                            System.out.print("Enter your choice: ");
                            int accChoice = scanner.nextInt();
                            switch (accChoice) {
                                case 1:
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    account.deposit(depositAmount);
                                    break;
                                case 2:
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    account.withdraw(withdrawAmount);
                                    break;
                                case 3:
                                    account.checkBalance();
                                    break;
                                case 4:
                                    System.out.println("Exiting to Main Menu...");
                                    break;
                                default:
                                    System.out.println("Invalid choice! Please try again.");
                            }
                            if (accChoice == 4) break;
                        }
                    }
                    break;

                case 3:
                    system.displayAllAccounts();
                    break;

                case 4:
                    System.out.println("Thank you for using the Banking System. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
