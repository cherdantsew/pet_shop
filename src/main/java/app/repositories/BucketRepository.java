package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketRepository extends DAO {

    public static final String GET_CUSTOMER_BUCKET_PROCEDURE = "{CALL GetCustomerBucket (?)}";

    public List<Product> getBucketByCustomerId(Connection connection, int user_id) throws SQLException {
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
        return new Product(resultSet.getInt("product_id"),
                resultSet.getString("product_category_id"),
                resultSet.getString("product_name"),
                resultSet.getString("product_price"),
                resultSet.getString("product_description"));
    }

    @Override
    public boolean insert(Connection connection, Object objectToInsert) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Connection connection, Object objectToUpdate) throws SQLException {
        return false;
    }

    @Override
    public Object getById(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Connection connection, Object objectToDelete) throws SQLException {
        return false;
    }

    @Override
    public List getAll(Connection connection) throws SQLException {
        return null;
    }
}
