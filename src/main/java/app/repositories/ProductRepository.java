package app.repositories;

import app.entities.Product;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class ProductRepository extends DAO<Product> {

    private static final String GET_ALL_STATEMENT = "SELECT * FROM products";
    private static final String BASE_SEARCH_QUERY = "select products.*, product_category.category_name from products join product_category on products.product_category_id = product_category.category_id";
    private static final String DELETE_FROM_PRODUCTS_BY_ID_STATEMENT = "DELETE from products WHERE product_id = ?";
    public static final String INSERT_INTO_PRODUCTS_STATEMENT = "INSERT INTO products (product_name, product_price, product_description, product_category_id) VALUES (?,?,?,?)";
    public static final String SELECT_AMOUNT_OF_PRODUCTS_IN_CATEGORY_STATEMENT = "SELECT count(*) AS products_amount FROM products WHERE product_category_id = ?";

    @Override
    public boolean insert(Connection connection, Product product) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_PRODUCTS_STATEMENT);
        preparedStatement.setString(1, product.getProductName());
        preparedStatement.setString(2, product.getProductPrice());
        preparedStatement.setString(3, product.getProductDescription());
        preparedStatement.setInt(4, Integer.parseInt(product.getProductCategory()));
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean update(Connection connection, Product objectToUpdate) {
        return false;
    }

    @Override
    public Product getById(Connection connection, int id) {
        return null;
    }

    @Override
    public boolean delete(Connection connection, Product objectToDelete) {
        return false;
    }

    public boolean deleteById(Connection connection, int productId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_PRODUCTS_BY_ID_STATEMENT);
        preparedStatement.setInt(1, productId);
        return preparedStatement.executeUpdate() == 1;
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

    public List<Product> search(Connection connection, String category, String name) throws SQLException {
        List<Product> productList = new ArrayList<>();
        PreparedStatement preparedStatement = buildSearchStatement(connection, category, name);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                productList.add(mapProduct(resultSet));
            }
        }
        return productList;
    }

    public Product getByNameAndCategoryId(Connection connection, String productName, int categoryId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE product_name = ? AND product_category_id = ?");
        preparedStatement.setString(1, productName);
        preparedStatement.setInt(2, categoryId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return mapProduct(resultSet);
            }
        }
        return null;
    }

    private boolean hasAnySearchParams(String category, String name) {
        return ObjectUtils.anyNotNull(category, name);
    }


    private Product mapProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("product_id"),
                resultSet.getString("product_category_id"),
                resultSet.getString("product_name"),
                resultSet.getString("product_price"),
                resultSet.getString("product_description"));
    }


    private PreparedStatement buildSearchStatement(Connection connection, String category, String namePattern) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder(BASE_SEARCH_QUERY);

        if (hasAnySearchParams(category, namePattern)) {
            if (isNotBlank(category)) {
                queryBuilder.append(" WHERE ");
                queryBuilder.append("category_name like ? ");
            }
            if (isNotBlank(namePattern)) {
                if (isNotBlank(category)) queryBuilder.append(" AND product_name like ?");
                else if (!isNotBlank(category)) queryBuilder.append(" WHERE product_name like ?");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            if (isNotBlank(category)) {
                preparedStatement.setString(paramIndex, category);
                paramIndex++;
            }
            if (isNotBlank(namePattern)) {
                preparedStatement.setString(paramIndex, "%" + namePattern + "%");
            }
            return preparedStatement;
        }
        return connection.prepareStatement(BASE_SEARCH_QUERY);
    }

    public int getAmountOfProductsInCategory(Connection connection, int categoryId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AMOUNT_OF_PRODUCTS_IN_CATEGORY_STATEMENT);
        preparedStatement.setInt(1, categoryId);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next())
                return resultSet.getInt("products_amount");
        }
        throw new SQLException("Cant perform sql request. Tried to get amount of products in category" + this.getClass().getName());
    }
}

