package app.repositories;

import app.entities.Order;
import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends DAO<Order> {

    private static final String INSERT_INTO_ORDERS_STATEMENT = "INSERT INTO orders (customer_id, order_date, product_id, status) VALUES (?, sysdate(), ?, ?)";
    private static final String GET_CUSTOMER_BUCKET_PROCEDURE = "{CALL GetCustomerBucket (?)}";

    public List<Product> getBucketProductsByCustomerId(Connection connection, int user_id) throws SQLException {
        List<Product> bucketProductsList = new ArrayList<>();
        CallableStatement callableStatement = connection.prepareCall(GET_CUSTOMER_BUCKET_PROCEDURE);
        callableStatement.setInt(1, user_id);
        try (ResultSet resultSet  = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                bucketProductsList.add(mapBucket(resultSet));
            }
        }
        return bucketProductsList;
    }

    private Product mapBucket(ResultSet resultSet) throws SQLException {
        return new Product(
                resultSet.getInt("product_id"),
                resultSet.getString("product_category_id"),
                resultSet.getString("product_name"),
                resultSet.getString("product_price"),
                resultSet.getString("product_description"));
    }

    @Override
    public boolean insert(Connection connection, Order order) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS_STATEMENT);
        preparedStatement.setInt(1, order.getCustomerId());
        preparedStatement.setInt(2, order.getProductId());
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
