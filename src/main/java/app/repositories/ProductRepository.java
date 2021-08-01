package app.repositories;

import app.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DAO<Product> {

    private static final String GET_PRODUCT_BY_CATEGORY_NAME_STATEMENT = "SELECT * FROM products WHERE product_category_id = (SELECT category_id FROM product_category WHERE category_name = ?)";
    private static final String GET_ALL_STATEMENT = "SELECT * FROM products";
    private static final String GET_PRODUCTS_BY_NAME_PREFIX = "SELECT * FROM products WHERE product_name LIKE (?)";

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
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STATEMENT);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                productList.add(mapProduct(resultSet));
            }
        }
        return productList;
    }

    public List<Product> getByCategoryName(Connection connection, String chosenCategoryName) throws SQLException {
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

    public List<Product> getByNamePrefix(Connection connection, String productNamePrefix) throws SQLException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCTS_BY_NAME_PREFIX);
        preparedStatement.setString(1, "%" + productNamePrefix + "%");
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

    public List<Product> searchByCategoryAndNamePrefix(Connection connection, String chosenCategoryName, String productNamePrefix) throws SQLException {
        List<Product> productList = new ArrayList<>();
        StringBuilder customerSearchStatement = new StringBuilder(GET_ALL_STATEMENT);
        int numberOfParameters = 0;
        boolean isCategoryChosen = false;
        boolean isNameChosen = false;

        if (chosenCategoryName != null) {
            numberOfParameters += 1;
            customerSearchStatement.append("product_category_id = (SELECT category_id FROM product_category WHERE category_name = ?)");
            isCategoryChosen = true;
        }
        if (productNamePrefix != null) {
            numberOfParameters += 1;
            customerSearchStatement.append(" AND product_name LIKE (?)");
            isNameChosen = true;
        }
        PreparedStatement preparedStatement;
        if (numberOfParameters == 0) {
            preparedStatement = connection.prepareStatement(GET_ALL_STATEMENT);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productList.add(mapProduct(resultSet));
                }
            }
        } else {
            preparedStatement = connection.prepareStatement(customerSearchStatement.toString());
            for (int i = 1; i <= numberOfParameters; i++) {
                if (isCategoryChosen) {
                    preparedStatement.setString(i, chosenCategoryName);
                    isCategoryChosen = false;
                    continue;
                }
                if (isNameChosen) {
                    preparedStatement.setString(i, productNamePrefix);
                    isNameChosen = false;
                }
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    productList.add(mapProduct(resultSet));
                }
            }
        }
        return productList;
    }
}

