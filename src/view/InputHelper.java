package view;

import java.util.Scanner;

public class InputHelper {

    public static String readString(Scanner scanner,String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }
}
