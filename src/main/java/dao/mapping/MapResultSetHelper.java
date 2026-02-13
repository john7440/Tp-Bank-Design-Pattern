package dao.mapping;

import factory.AccountFactory;
import model.*;

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

        if (account instanceof CurrentAccount currentAccount){
            double overdraftLimit = rs.getDouble("overdraft_limit");
            currentAccount.setOverdraftLimit(overdraftLimit);
        } else if (account instanceof SavingsAccount savingsAccount) {
            double interestRate = rs.getDouble("interest_rate");
            savingsAccount.setInterestRate(interestRate);
        }

        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            account.setCreatedAt(timestamp.toLocalDateTime());
        }
        return account;
    }

    public Client mapResultSetToClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setEmail(rs.getString("email"));
        client.setPhone(rs.getString("phone"));

        Timestamp timestamp = rs.getTimestamp("created_at");
        if (timestamp != null) {
            client.setCreatedAt(timestamp.toLocalDateTime());
        }
        return client;
    }

    public Operation mapResultSetToOperation(ResultSet rs) throws SQLException {
        Operation operation = new Operation();
        operation.setId(rs.getLong("id"));
        operation.setType(rs.getString("type"));
        operation.setAmount(rs.getDouble("amount"));
        operation.setAccountId(rs.getLong("account_id"));

        Timestamp timestamp = rs.getTimestamp("operation_date");
        if (timestamp != null) {
            operation.setOperationDate(timestamp.toLocalDateTime());
        }
        return operation;
    }
}
