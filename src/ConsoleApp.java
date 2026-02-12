import service.BankService;
import view.MenuView;

import java.util.Scanner;

public class ConsoleApp {
    private static BankService bankService = new BankService();
    private static Scanner scanner = new Scanner(System.in);
    private static MenuView menuView = new MenuView(bankService, scanner);

    public static void main(String[] args) {
        displayWelcomeBanner();

    }

    private static void displayWelcomeBanner() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("        WELCOME");
        System.out.println("=".repeat(60));
    }
}
