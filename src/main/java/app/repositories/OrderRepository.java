package app.repositories;

import app.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderRepository extends DAO<Order> {

    private static final String INSERT_INTO_ORDERS_STATEMENT = "INSERT INTO orders (customer_id, order_date, product_id, status) VALUES (?, sysdate(), ?, ?)";

    @Override
    public boolean insert(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_STATEMENT);
        preparedStatement.setInt(1, order.getCustomerId());
        preparedStatement.setInt(2, order.getProductId());
       // Instant.now()
        preparedStatement.setString(3, order.getStatus());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean update(Connection connection, Order objectToUpdate) throws SQLException {
        return false;
    }

    @Override
    public Order getById(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Connection connection, Order objectToDelete) throws SQLException {
        return false;
    }

    @Override
    public List<Order> getAll(Connection connection) throws SQLException {
        return null;
    }
}
