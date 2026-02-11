package dao.mapping;

import factory.AccountFactory;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MapResultSetHelper {

    public Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Long id = rs.getLong("id");
        String number = rs.getString("number");
        double balance = rs.getDouble("balance");
        String type = rs.getString("type");
        Long clientId = rs.getLong("client_id");

        Account account = AccountFactory.createAccount(type,id, number, balance, clientId);

        if (account instanceof CurrentAccount){
            double overdraftLimit = rs.getDouble("overdraft_limit");
            ((CurrentAccount)account).setOverdraftLimit(overdraftLimit);
        } else if (account instanceof SavingsAccount) {
            double interestRate = rs.getDouble("interest_rate");
            ((SavingsAccount)account).setInterestRate(interestRate);
        }

        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            account.setCreatedAt(timestamp.toLocalDateTime());
        }
        return account;
    }
}
