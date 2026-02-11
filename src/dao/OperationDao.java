package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Operation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDao {
    private Connection connection;
    private MapResultSetHelper helper =  new MapResultSetHelper();

    public OperationDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<Operation> findAll() {
        List<Operation> operations = new ArrayList<>();
        String sql= QueryBuilder
                .select("*")
                .from("operations")
                .orderBy("operation_date DESC")
                .build();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                operations.add(helper.mapResultSetToOperation(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all operations",e);
        }
        return operations;
    }

}
