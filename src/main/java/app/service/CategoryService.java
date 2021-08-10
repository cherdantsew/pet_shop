package app.service;

import app.converters.ProductCategoryConverter;
import app.dto.ProductCategoryDTO;
import app.entities.ProductCategory;
import app.exceptions.CategoryValidationException;
import app.exceptions.TransactionExecutionException;
import app.exceptions.ValidationException;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {

    private final ProductRepository productRepository = new ProductRepository();
    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private final ProductCategoryConverter productCategoryConverter = new ProductCategoryConverter();

    public List<ProductCategoryDTO> getAllCategories() {
        TransactionHandler<List<ProductCategory>> transactionHandler = new TransactionHandler<>(productCategoryRepository::getAll);
        try {
            return productCategoryConverter.toProductCategoryDTOList(transactionHandler.execute());
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public boolean removeCategory(String categoryId) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> {
            if (hasProducts(Integer.parseInt(categoryId))) {
                throw new ValidationException(String.format("Please remove all products from category %s", categoryId));
            }
            return productCategoryRepository.delete(connection, categoryId);
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public boolean addCategory(String newCategoryName) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> {
            if (productCategoryRepository.getByName(connection, newCategoryName) != null) {
                throw new CategoryValidationException("Unable to add new category. Category with that name already exists.");
            }
            ProductCategory productCategory = new ProductCategory(newCategoryName);
            return productCategoryRepository.insert(connection, productCategory);
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    private boolean hasProducts(int categoryId) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> productRepository.getAmountOfProductsInCategory(connection, categoryId) > 0);
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
