package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketRepository extends DAO {


    public static final String GET_CUSTOMER_BUCKET_PROCEDURE = "{CALL GetCustomerBucket (?)}";

    @Override
    public boolean insert(Object objectToInsert) {
        return false;
    }

    @Override
    public boolean update(Object objectToUpdate) {
        return false;
    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public boolean delete(Object objectToDelete) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }

    public List<Product> getBucketByUserId (int user_id) throws SQLException {

        List<Product> bucketProductsList = new ArrayList();

        try (Connection connection = getConnection()) {

            String GetCustomerBucketURL = GET_CUSTOMER_BUCKET_PROCEDURE;

            CallableStatement callableStatement = connection.prepareCall(GetCustomerBucketURL);

            callableStatement.setInt(1, user_id);
            callableStatement.execute();

            ResultSet resultSet = callableStatement.getResultSet();

            if (resultSet.next()) {
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

}
