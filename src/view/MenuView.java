package view;

import service.BankService;

import java.util.Scanner;

public class MenuView {
    private BankService  bankService;
    private Scanner scanner;

    private ClientView clientView;
    private AccountView accountView;
    private OperationView operationView;

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


}
