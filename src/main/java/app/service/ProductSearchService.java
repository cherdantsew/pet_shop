package app.service;

import app.converters.ProductCategoryConverter;
import app.converters.ProductConverter;
import app.dto.ProductCategoryDTO;
import app.dto.ProductDTO;
import app.entities.Product;
import app.entities.ProductCategory;
import app.exceptions.TransactionExecutionException;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductSearchService {

    private final ProductRepository productRepository = new ProductRepository();
    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();

    private final ProductConverter productConverter = new ProductConverter();
    private final ProductCategoryConverter productCategoryConverter = new ProductCategoryConverter();

    public List<ProductDTO> search(String chosenCategoryName, String productNamePrefix) throws SQLException {
        TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(connection -> productRepository.search(connection, chosenCategoryName, productNamePrefix));
        return productConverter.toProductDTOList(transactionHandler.execute());
    }

    public List<ProductDTO> getAllProducts() {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>(productRepository::getAll);
            return productConverter.toProductDTOList(transactionHandler.execute());
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public List<ProductCategoryDTO> getProductCategories() {
        try {
            TransactionHandler<List<ProductCategory>> transactionHandler = new TransactionHandler<>(productCategoryRepository::getAll);
            return productCategoryConverter.toProductCategoryDTOList(transactionHandler.execute());
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
