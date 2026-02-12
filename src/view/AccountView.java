package view;

import service.BankService;

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

    }
}
