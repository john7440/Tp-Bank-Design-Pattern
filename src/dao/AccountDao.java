package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Account;

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

}
