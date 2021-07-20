package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DAO<Product> {

    public static final String GET_PRODUCT_BY_CATEGORY_NAME_STATEMENT = "SELECT * FROM products WHERE product_category_id = (SELECT category_id FROM product_category WHERE category_name = ?)";

    @Override
    public boolean insert(Connection connection, Product objectToInsert) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Connection connection, Product objectToUpdate) throws SQLException {
        return false;
    }

    @Override
    public Product getById(Connection connection, int id) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Connection connection, Product objectToDelete) throws SQLException {
        return false;
    }

    @Override
    public List<Product> getAll(Connection connection) throws SQLException {
        return null;
    }

    public List getByCategoryName(Connection connection, String chosenCategoryName) throws SQLException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT_BY_CATEGORY_NAME_STATEMENT);
        preparedStatement.setString(1, chosenCategoryName);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                productList.add(mapProduct(resultSet));
            }
        }
        return productList;
    }

    private Product mapProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("product_id"),
                resultSet.getString("product_category_id"),
                resultSet.getString("product_name"),
                resultSet.getString("product_price"),
                resultSet.getString("product_description"));
    }
}
