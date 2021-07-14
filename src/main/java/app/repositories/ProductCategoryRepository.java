package app.repositories;

import app.entities.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryRepository extends DAO<ProductCategory> {

    public static final String GET_ALL_STATEMENT = "SELECT * FROM product_category";
    Connection connection = getConnection();
    TransactionHandler transactionHandler = new TransactionHandler();

    @Override
    public boolean insert(ProductCategory productCategory) {
        return false;
    }

    @Override
    public boolean update(ProductCategory productCategory) {
        return false;
    }

    @Override
    public ProductCategory getById(int id) {
        return null;
    }

    @Override
    public boolean delete(ProductCategory productCategory) {
        return false;
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> categoryList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_STATEMENT);
        ResultSet resultSet = transactionHandler.handleQueryTransaction(preparedStatement, connection);
        while (resultSet.next()) {
            categoryList.add(mapProductCategory(resultSet));
        }
        return categoryList;
    }

    private ProductCategory mapProductCategory(ResultSet resultSet) throws SQLException {
        return new ProductCategory(resultSet.getInt("category_id"), resultSet.getString("category_name"));
    }
}
