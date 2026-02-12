package view;

import model.Account;
import model.Client;
import model.CurrentAccount;
import model.SavingsAccount;
import service.BankService;

import java.rmi.ConnectIOException;
import java.util.List;
import java.util.Scanner;

public class AccountView implements View{
    private BankService bankService;
    private Scanner scanner;

    public AccountView(BankService bankService, Scanner scanner) {
        this.bankService = bankService;
        this.scanner = scanner;
    }

    @Override
    public void display() {
        displayClientAccounts();
    }

    public void displayClientAccounts() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                CLIENT ACCOUNTS");
        System.out.println("=".repeat(60));

        Long clientId = InputHelper.readLong(scanner, "Enter Client ID: ");
        Client client =  bankService.findClientById(clientId);

        System.out.println("\nClient: " + client.getName() + "\nEmail: " + client.getEmail());
        System.out.println("-".repeat(60));

        List<Account> accounts = bankService.getClientAccounts(clientId);

        if (accounts.isEmpty()) {
            System.out.println("No accounts found for this client");
        } else {
            printAccountsTables(accounts);
        }
    }

    public void printAccountsTables(List<Account> accounts) {
        System.out.printf("%-10s | %-10s | %-15s | %-20s%n",
                "Number", "Type", "Balance", "Details");
        System.out.println("-".repeat(60));

        for (Account account : accounts) {
            String details = getAccountDetails(account);

            System.out.printf("%-10s | %-10s | %,13.2f€ | %-20s%n",
                    account.getNumber(),
                    account.getType(),
                    account.getBalance(),
                    details
            );
        }
        System.out.println("=".repeat(60));
        System.out.println("Total accounts: " + accounts.size());
    }

    public String getAccountDetails(Account account) {
        if (account instanceof CurrentAccount){
            CurrentAccount ca =  (CurrentAccount) account;
            return "Overdraft: " + String.format("%.0f€", ca.getOverdraftLimit());
        } else if (account instanceof SavingsAccount) {
            SavingsAccount sa =  (SavingsAccount) account;
            return "Interest; " + String.format("%.2f%%", sa.getInterestRate());
        }
        return "";
    }
}
