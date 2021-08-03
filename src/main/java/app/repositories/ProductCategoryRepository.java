package app.repositories;

import app.entities.ProductCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryRepository extends DAO<ProductCategory> {

    private static final String GET_ALL_STATEMENT = "SELECT * FROM product_category";
    private static final String DELETE_BY_CATEGORY_ID_STATEMENT = "DELETE FROM product_category WHERE category_id = ?";
    private static final String INSERT_STATEMENT = "INSERT INTO product_category (category_name) VALUES (?)";

    public boolean delete(Connection connection, int categoryId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_CATEGORY_ID_STATEMENT);
        preparedStatement.setInt(1, categoryId);
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean insert(Connection connection, ProductCategory productCategory) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);
        preparedStatement.setString(1, productCategory.getCategoryName());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean update(Connection connection, ProductCategory productCategory) {
        return false;
    }

    @Override
    public ProductCategory getById(Connection connection, int id) {
        return null;
    }

    @Override
    public boolean delete(Connection connection, ProductCategory productCategory) {
        return false;
    }

    @Override
    public List<ProductCategory> getAll(Connection connection) throws SQLException {
        List<ProductCategory> categoryList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STATEMENT);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                categoryList.add(mapProductCategory(resultSet));
            }
        }
        return categoryList;
    }

    private ProductCategory mapProductCategory(ResultSet resultSet) throws SQLException {
        return new ProductCategory(resultSet.getInt("category_id"), resultSet.getString("category_name"));
    }
}
