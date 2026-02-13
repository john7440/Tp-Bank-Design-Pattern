package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import exception.AccountNotFoundException;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final Connection connection;
    private final MapResultSetHelper helper = new MapResultSetHelper();
    private static final String ACCOUNT = "accounts";

    public AccountDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /*-------------------méthodes---------------------------*/
    public List<Account> findAll(){
        List<Account> accounts = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from(ACCOUNT)
                .orderBy("number")
                .build();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                accounts.add(helper.mapResultSetToAccount(rs));
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException("Error fetching accounts");
        }
        return accounts;
    }

    public Account findById(long id){
        String sql = QueryBuilder
                .select("*")
                .from(ACCOUNT)
                .where("id = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return helper.mapResultSetToAccount(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException("Error fetching account by id ");
        }
        return null;
    }

    public Account findByNumber(String number){
        String sql = QueryBuilder
                .select("*")
                .from(ACCOUNT)
                .where("number = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, number);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return helper.mapResultSetToAccount(rs);
                }
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException("Error fetching account by number ");
        }
        return null;
    }

    public List<Account> findByClientId(Long clientId){
        List<Account> accounts = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from(ACCOUNT)
                .where("client_id = ?")
                .orderBy("number")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, clientId);

            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    accounts.add(helper.mapResultSetToAccount(rs));
                }
            }
        } catch (SQLException e) {
            throw new AccountNotFoundException("Error fetching account for client: " + clientId);
        }
        return accounts;
    }

    public void update(Account account){
        String sql = QueryBuilder
                .update(ACCOUNT)
                .set("balance", "overdraft_limit", "interest_rate")
                .where("id = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1,account.getBalance() );

            if (account instanceof CurrentAccount currentAccount){
                stmt.setDouble(2, currentAccount.getOverdraftLimit());
                stmt.setNull(3, Types.DECIMAL);
            } else if (account instanceof SavingsAccount savingsAccount) {
                stmt.setNull(2, Types.DECIMAL);
                stmt.setDouble(3,savingsAccount.getInterestRate());
            }
            stmt.setLong(4, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new AccountNotFoundException("Error updating account ");
        }
    }

}
