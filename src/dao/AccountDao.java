package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private Connection connection;
    private MapResultSetHelper helper = new MapResultSetHelper();

    public AccountDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

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


}
