package view;

import service.BankService;

import java.util.Scanner;

public class MenuView {
    private final BankService bankService;
    private final Scanner scanner;

    private final ClientView clientView;
    private final AccountView accountView;
    private final OperationView operationView;

    public MenuView(BankService bankService, Scanner scanner) {
        this.bankService = bankService;
        this.scanner = scanner;
        this.clientView = new ClientView(bankService,scanner);
        this.accountView = new AccountView(bankService,scanner);
        this.operationView = new OperationView(bankService,scanner);
    }

    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                     MAIN MENU");
        System.out.println("=".repeat(60));
        System.out.println("1. Display all clients");
        System.out.println("2. Display client accounts");
        System.out.println("3. Operation (deposit/withdrawal/history)");
        System.out.println("4. Check account balance");
        System.out.println("0. Exit");
        System.out.println("=".repeat(60));
    }

    public int getUserChoice(){
        return InputHelper.readInt(scanner,"Enter your choice: ");
    }

    public void handleChoice(int choice){
        switch (choice){
            case 1:
                clientView.display();
                break;
            case 2:
                accountView.displayClientAccounts();
                break;
            case 3:
                operationView.display();
                break;
            case 4:
                accountView.displayBalance();
                break;
            case 0:
                System.out.println("\nGoodbye");
                break;
            default:
                System.out.println("Invalid choice, please try again");

        }
    }

    public void waitForUser(){
        System.out.println("\nPress enter to continue..");
        scanner.nextLine();
    }

}
