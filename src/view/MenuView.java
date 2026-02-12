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
}
