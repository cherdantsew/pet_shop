package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DAO {

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

    public List getByCategoryName(String chosenCategoryName) {
        List<Product> productList = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            String getProductsURL = "SELECT * FROM products WHERE product_category_id = (SELECT category_id FROM product_category WHERE category_name = '" + chosenCategoryName + "')";

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(getProductsURL);

            while (resultSet.next()) {
                productList.add(new Product(resultSet.getInt("product_id"),
                        resultSet.getString("product_category_id"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_price"),
                        resultSet.getString("product_description"))
                );
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("ERROR WHILE GETTING ALL PRODUCTS");
        }

        return productList;
    }
}
