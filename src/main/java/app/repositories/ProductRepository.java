package app.repositories;

import app.entities.Product;
import app.entities.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends DAO<Product> {

    public static final String GET_PRODUCT_BY_CATEGORY_NAME_STATEMENT = "SELECT * FROM products WHERE product_category_id = (SELECT category_id FROM product_category WHERE category_name = ?)";

    @Override
    public boolean insert(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public Product getById(int id) {
        return null;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public List getAll() {
        return null;
    }

    public List getByCategoryName(String chosenCategoryName) throws SQLException {
        List<Product> productList = new ArrayList();

        try (Connection connection = getConnection()) {

            PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_CATEGORY_NAME_STATEMENT);
            statement.setString(1, chosenCategoryName);

            ResultSet resultSet = statement.executeQuery();

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
