package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Account;
import model.CurrentAccount;
import model.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private Connection connection;
    private MapResultSetHelper helper = new MapResultSetHelper();

    public AccountDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /*-------------------méthodes---------------------------*/
    public List<Account> findAll(){
        List<Account> accounts = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from("accounts")
                .orderBy("number")
                .build();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                accounts.add(helper.mapResultSetToAccount(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching accounts" ,e);
        }
        return accounts;
    }

    public Account findById(long id){
        String sql = QueryBuilder
                .select("*")
                .from("accounts")
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
            throw new RuntimeException("Error fetching account by id " ,e);
        }
        return null;
    }

    public Account findByNumber(String number){
        String sql = QueryBuilder
                .select("*")
                .from("accounts")
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
            throw new RuntimeException("Error fetching account by number " , e);
        }
        return null;
    }

    public List<Account> findByClientId(Long clientId){
        List<Account> accounts = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from("accounts")
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
            throw new RuntimeException("Error fetching account for client: " + clientId ,e);
        }
        return accounts;
    }

    public void update(Account account){
        String sql = QueryBuilder
                .update("accounts")
                .set("balance", "overdraft_limit", "interest_rate")
                .where("id = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setDouble(1,account.getBalance() );

            if (account instanceof CurrentAccount){
                stmt.setDouble(2, ((CurrentAccount) account).getOverdraftLimit());
                stmt.setNull(3, Types.DECIMAL);
            } else if (account instanceof SavingsAccount) {
                stmt.setNull(2, Types.DECIMAL);
                stmt.setDouble(3,((SavingsAccount) account).getInterestRate());
            }
            stmt.setLong(4, account.getId());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Error updating account " ,e);
        }
    }

}
