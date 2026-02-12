package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import model.Operation;

import java.sql.*;
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

    public List<Operation> findByAccountId(Long accountId) {
        List<Operation> operations = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from("operations")
                .where("account_id = ?")
                .orderBy("operation_date DESC")
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setLong(1, accountId);

            try (ResultSet rs = stmt.executeQuery()){
                while (rs.next()){
                    operations.add(helper.mapResultSetToOperation(rs));
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Error fetching operations for: "+ accountId,e);
        }
        return operations;
    }

    public void save(Operation operation) {
        String sql = QueryBuilder
                .insert("operations")
                .columns("type", "amount", "account_id")
                .values(3)
                .build();

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, operation.getType());
            stmt.setDouble(2, operation.getAmount());
            stmt.setLong(3, operation.getAccountId());

            stmt.executeUpdate();

            try (ResultSet genKeys = stmt.getGeneratedKeys()){
                if (genKeys.next()){
                    operation.setId(genKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving operation",e);
        }
    }

}
