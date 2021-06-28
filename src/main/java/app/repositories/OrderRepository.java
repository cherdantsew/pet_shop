package app.repositories;

import app.entities.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderRepository extends DAO<Order> {

    public static final String INSERT_INTO_ORDERS_STATEMENT = "INSERT INTO orders (customer_id, order_date, product_id, status) VALUES (?, sysdate(), ?, ?)";

    @Override
    public boolean insert(Order order) throws SQLException {

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_ORDERS_STATEMENT);
            statement.setInt(1, order.getCustomerId());
            statement.setInt(2, order.getProductId());
            statement.setString(3, order.getStatus());

            return statement.executeUpdate() == 1;
        }
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
