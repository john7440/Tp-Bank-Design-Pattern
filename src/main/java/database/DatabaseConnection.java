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

    public static synchronized DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(URL,USER,PASSWORD);
            }
        } catch (SQLException e){
            throw new RuntimeException("Connection failed",e);
        }
        return connection;
    }

    public void closeConnection(){
        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing connection!",e);
        }
    }
}
