package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private Connection connection;
    private MapResultSetHelper helper =  new MapResultSetHelper();

    public ClientDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from("clients")
                .orderBy("name")
                .build();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                clients.add(helper.mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all clients",e);
        }
        return clients;
    }

}
