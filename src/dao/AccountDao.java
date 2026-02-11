package dao;

import database.DatabaseConnection;
import java.sql.Connection;

public class AccountDao {
    private Connection connection;

    public AccountDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }


}
