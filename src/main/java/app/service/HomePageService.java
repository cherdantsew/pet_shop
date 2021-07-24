package app.service;

import app.entities.Order;
import app.entities.Product;
import app.entities.ProductCategory;
import app.exceptions.TransactionExecutionException;
import app.repositories.BucketRepository;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class HomePageService {
    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final BucketRepository bucketRepository = new BucketRepository();

    public List<ProductCategory> getProductCategories() {
        try {
            TransactionHandler<List<ProductCategory>> transactionHandler = new TransactionHandler<>(productCategoryRepository::getAll);
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public List<Product> getProductsByCategoryName(String chosenCategoryName) {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(connection -> productRepository.getByCategoryName(connection, chosenCategoryName));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public boolean addBucketItem(Order order) {
        try {
            TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> bucketRepository.insert(connection, order));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
