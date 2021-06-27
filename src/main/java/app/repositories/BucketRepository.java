package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BucketRepository extends DAO {

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/javashema?characterEncoding=latin1";
    public static final String ROOT_LOGIN = "root";
    public static final String ROOT_PASSWORD = "root";


    @Override
    public boolean insert(Object objectToInsert) {



        return false;
    }

    @Override
    public void update(Object objectToUpdate) {

    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public void delete(Object objectToDelete) {

    }

    @Override
    public List getAll() {
        return null;
    }

    public List<Product> getBucketByUserId (int user_id) {

        List<Product> bucketProductsList = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            String GetCustomerBucketURL = "{CALL GetCustomerBucket (?)}";

            CallableStatement callableStatement = connection.prepareCall(GetCustomerBucketURL);

            callableStatement.setInt(1, user_id);
            callableStatement.execute();
            ResultSet resultSet = callableStatement.getResultSet();
            while (resultSet.next()) {
                bucketProductsList.add(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("product_category_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_price"),
                        resultSet.getString("product_description"))
                        );
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("ERROR WHILE TRYING TO GET BUCKET ITEMS FOR USER ID = " + user_id);
        }

        return bucketProductsList;
    }

}
