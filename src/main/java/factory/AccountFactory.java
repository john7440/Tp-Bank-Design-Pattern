package factory;

import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;

public class AccountFactory {

    private AccountFactory() {
        throw new IllegalStateException("Utility class");
    }

    /*--------------------méthodes----------------*/
    public static Account createAccount(String type,Long id, String number, double balance, Long clientId) {
        if (type == null || type.isEmpty()){
            throw new IllegalArgumentException("Type is null or empty");
        }

        return switch (type.toUpperCase()) {
            case "CURRENT" -> new CurrentAccount(id, number, balance, clientId, 0.0);
            case "SAVINGS" -> new SavingsAccount(id, number, balance, clientId, 0.0);
            default -> throw new IllegalArgumentException("Unknown account type " + type);
        };
    }
}
