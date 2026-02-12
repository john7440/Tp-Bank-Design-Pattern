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
}
