package dao;

import dao.builder.QueryBuilder;
import dao.mapping.MapResultSetHelper;
import database.DatabaseConnection;
import exception.InvalidOperationException;
import exception.OperationNotFoundException;
import model.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDao {
    private final Connection connection;
    private final MapResultSetHelper helper =  new MapResultSetHelper();
    private static final String OPERATIONS = "operations";

    public OperationDao() {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    public List<Operation> findAll() {
        List<Operation> operations = new ArrayList<>();
        String sql= QueryBuilder
                .select("*")
                .from(OPERATIONS)
                .orderBy("operation_date DESC")
                .build();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                operations.add(helper.mapResultSetToOperation(rs));
            }
        } catch (SQLException e) {
            throw new OperationNotFoundException("Error fetching all operations");
        }
        return operations;
    }

    public List<Operation> findByAccountId(Long accountId) {
        List<Operation> operations = new ArrayList<>();
        String sql = QueryBuilder
                .select("*")
                .from(OPERATIONS)
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
            throw new OperationNotFoundException("Error fetching operations for: "+ accountId);
        }
        return operations;
    }

    public void save(Operation operation) {
        String sql = QueryBuilder
                .insert(OPERATIONS)
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
            throw new InvalidOperationException("Error saving operation");
        }
    }

}
