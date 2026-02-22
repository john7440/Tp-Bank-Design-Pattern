package view;

import service.OperationService;

import java.util.Scanner;

public class OperationView implements View{
    private final OperationService operationService;
    private final Scanner scanner;

    public OperationView(OperationService operationService, Scanner scanner) {
        this.operationService = operationService;
        this.scanner = scanner;
    }

    @Override
    public void display() {
        displayOperationMenu();
    }

    private void displayOperationMenu(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("             OPERATIONS MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Make a deposits");
        System.out.println("2. Make a withdrawal");
        System.out.println("3. Display account history");
        System.out.println("0. Back tpo main menu");
        System.out.println("=".repeat(60));

        int choice = InputHelper.readInt(scanner,"Enter your choice: ");

        switch(choice){
            case 1:
                performDeposit();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                displayAccountHistory();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice");
        }
    }
    public void performDeposit(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("               DEPOSIT");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account num: ");
        double amount = InputHelper.readDouble(scanner, "Enter amount to deposit: ");

        operationService.deposit(accountNumber, amount);
    }

    public void performWithdrawal(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                 WITHDRAWAL");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account number: ");
        double amount = InputHelper.readDouble(scanner, "Enter amount to withdrawal: ");
        operationService.withdraw(accountNumber, amount);
    }

    public void displayAccountHistory(){
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                ACCOUNT HISTORY");
        System.out.println("=".repeat(60));

        String accountNumber = InputHelper.readString(scanner, "Enter account number: ");
        operationService.displayAccountHistory(accountNumber);
    }
}
