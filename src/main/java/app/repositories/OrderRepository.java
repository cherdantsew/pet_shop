package app.repositories;

import app.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderRepository extends DAO<Order> {

    public static final String INSERT_INTO_ORDERS_STATEMENT = "INSERT INTO orders (customer_id, order_date, product_id, status) VALUES (?, sysdate(), ?, ?)";
    Connection connection = getConnection();

    @Override
    public boolean insert(Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_STATEMENT);
        preparedStatement.setInt(1, order.getCustomerId());
        preparedStatement.setInt(2, order.getProductId());
        preparedStatement.setString(3, order.getStatus());
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection, () -> preparedStatement.executeUpdate() == 1);
        return transactionHandler.execute();

    }

    @Override
    public boolean update(Order order) {
        return false;
    }

    @Override
    public Order getById(int id) {
        return null;
    }

    @Override
    public boolean delete(Order order) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }
}
