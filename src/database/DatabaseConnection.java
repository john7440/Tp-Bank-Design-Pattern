package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static  DatabaseConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/tpbank";
    private static final String USER = "tpbank_user";
    private static final String PASSWORD = "TpBank2026!";

    private DatabaseConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Connected");
        } catch (ClassNotFoundException e) {
            throw  new RuntimeException("Mysql driver not found",e);
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed",e);
        }
    }
}
