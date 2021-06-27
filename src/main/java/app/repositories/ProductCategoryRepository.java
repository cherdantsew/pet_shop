package app.repositories;

import app.entities.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryRepository extends DAO {

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
    public List<ProductCategory> getAll() {
        List<ProductCategory> categoryList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            String GETALL_URL = ("SELECT * FROM product_category;");

            ResultSet resultSet = statement.executeQuery(GETALL_URL);

            while (resultSet.next()) {
                categoryList.add(new ProductCategory(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")));
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("ERROR WHILE TRYING TO GET ALL USERS");
        }

        return categoryList;
    }
}
