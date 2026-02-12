import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import service.BankService;
import view.MenuView;

import java.util.Scanner;

public class ConsoleApp {
    private static BankService bankService = new BankService();
    private static Scanner scanner = new Scanner(System.in);
    private static MenuView menuView = new MenuView(bankService, scanner);

    public static void main(String[] args) {
        displayWelcomeBanner();
        boolean running = true;
        while (running) {
            menuView.displayMainMenu();
            int choice = menuView.getUserChoice();

            try {
                if (choice == 0) {
                    menuView.handleChoice(choice);
                    running = false;
                } else {
                    menuView.handleChoice(choice);
                    menuView.waitForUser();
                }
            } catch (AccountNotFoundException | InsufficientBalanceException e) {
                System.out.println("Error" + e.getMessage());
                menuView.waitForUser();
            } catch (Exception e){
                System.out.println("Unexpected error" + e.getMessage());
                menuView.waitForUser();
            }
        }
        scanner.close();
    }

    private static void displayWelcomeBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        WELCOME");
        System.out.println("=".repeat(60));
    }
}
