package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import exception.ClientNotFoundException;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    private final Connection connection;
    private final MapResultSetHelper helper =  new MapResultSetHelper();
    private static final String CLIENT = "clients";

    public ClientDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from(CLIENT)
                .orderBy("name")
                .build();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                clients.add(helper.mapResultSetToClient(rs));
            }
        } catch (SQLException e) {
            throw new ClientNotFoundException("Error fetching all clients");
        }
        return clients;
    }

    public Client findById(Long id){
        String sql = QueryBuilder
                .select("*")
                .from(CLIENT)
                .where("id = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return  helper.mapResultSetToClient(rs);
                }
            }
        } catch (SQLException e) {
            throw new ClientNotFoundException("Error fetching client by id");
        }
        return null;
    }

    public Client findByEmail(String email) {
        String sql = QueryBuilder
                .select("*")
                .from(CLIENT)
                .where("email = ?")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return helper.mapResultSetToClient(rs);
                }
            }
        } catch (SQLException e) {
            throw new ClientNotFoundException("Error fetching client by email");
        }
        return null;
    }

}
