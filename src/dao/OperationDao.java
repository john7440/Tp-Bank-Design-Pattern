package dao;

import database.DatabaseConnection;

import java.sql.Connection;

public class OperationDao {
    private Connection connection;

    public OperationDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }


}
