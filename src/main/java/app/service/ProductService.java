package app.service;

import app.entities.Product;
import app.entities.ProductCategory;
import app.exceptions.TransactionExecutionException;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();
    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();

    public List<Product> getProductsByCategoryName(String chosenCategoryName) {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(connection -> productRepository.getByCategoryName(connection, chosenCategoryName));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public List<Product> getAllProducts() {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(connection -> productRepository.getAll(connection));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public List<ProductCategory> getProductCategories() {
        try {
            TransactionHandler<List<ProductCategory>> transactionHandler = new TransactionHandler<>(productCategoryRepository::getAll);
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public List<Product> getProductsByNamePrefix(String productNamePrefix) {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(connection -> productRepository.getByNamePrefix(connection, productNamePrefix));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
