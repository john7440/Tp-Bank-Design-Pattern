import database.DatabaseConnection;
import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import service.AccountService;
import service.ClientService;
import service.OperationService;
import view.MenuView;

import java.util.Scanner;

public class ConsoleApp {
    private static final AccountService accountService = new AccountService();
    private static final ClientService clientService = new ClientService();
    private static final OperationService operationService = new OperationService();
    private static final Scanner scanner = new Scanner(System.in);
    private static final MenuView menuView = new MenuView(clientService,operationService,accountService, scanner);

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
        closeRessources();
    }

    private static void displayWelcomeBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("                        WELCOME");
        System.out.println("=".repeat(60));
    }

    private static void closeRessources() {
        DatabaseConnection.getInstance().closeConnection();
    }
}
