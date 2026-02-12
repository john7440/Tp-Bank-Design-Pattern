package view;

import service.BankService;

import java.util.Scanner;

public class OperationView implements View{
    private BankService bankService;
    private Scanner scanner;

    public OperationView(BankService bankService, Scanner scanner) {
        this.bankService = bankService;
        this.scanner = scanner;
    }

    @Override
    public void display() {

    }

    public void performDeposit(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               DEPOSIT");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account number: ");
        double amount = InputHelper.readDouble(scanner, "Enter amount to deposit: ");

        bankService.deposit(accountNumber, amount);
    }

    public void performWithdrawal(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 WITHDRAWAL");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account number: ");
        double amount = InputHelper.readDouble(scanner, "Enter amount to withdrawal: ");
        bankService.withdraw(accountNumber, amount);
    }

    public void displayAccountHistory(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                ACCOUNT HISTORYY");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account number: ");
        bankService.getAccountHistory(accountNumber);
    }
}
