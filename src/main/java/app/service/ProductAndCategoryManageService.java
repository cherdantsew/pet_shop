package app.service;

import app.exceptions.TransactionExecutionException;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;
import org.apache.commons.collections.CollectionUtils;

import java.sql.SQLException;

public class ProductAndCategoryManageService {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private static final ProductSearchService productSearchService = new ProductSearchService();

    public boolean deleteCategory(String categoryNameToDelete) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> {
            if (CollectionUtils.isNotEmpty(productSearchService.search(categoryNameToDelete, null))){
                return false;
            }
            return productCategoryRepository.delete(connection, categoryNameToDelete);
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public boolean deleteProduct(Integer productIdToDelete) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> productRepository.deleteById(connection, productIdToDelete));
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
