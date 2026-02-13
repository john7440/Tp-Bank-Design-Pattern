package view;

import java.util.Scanner;

public class InputHelper {

    private InputHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static String readString(Scanner scanner,String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    public static int readInt(Scanner scanner,String prompt) {
        while(true){
            try{
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return  Integer.parseInt(input);
            } catch(NumberFormatException e){
                System.out.println("Enter a valid number");
            }
        }
    }

    public static double readDouble(Scanner scanner,String prompt) {
        while(true){
            try{
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return  Double.parseDouble(input);
            } catch(NumberFormatException e){
                System.out.println("Enter a valid amount");
            }
        }
    }

    public static Long readLong(Scanner scanner,String prompt) {
        while(true){
            try{
                System.out.println(prompt);
                String input = scanner.nextLine().trim();
                return  Long.parseLong(input);
            }  catch(NumberFormatException e){
                System.out.println("Enter a valid id");
            }
        }
    }
}
