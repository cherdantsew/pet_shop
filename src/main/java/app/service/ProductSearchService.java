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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<ProductCategoryDTO, List<ProductDTO>> getCategoryWithProductsMap() {
        TransactionHandler<Map<ProductCategoryDTO, List<ProductDTO>>> transactionHandler = new TransactionHandler<>(connection -> {
            Map <ProductCategoryDTO, List<ProductDTO>> categoryToProductsMap = new HashMap<>();
            List <ProductCategoryDTO> productCategoryDTOList = productCategoryConverter.toProductCategoryDTOList(productCategoryRepository.getAll(connection));
            for(ProductCategoryDTO productCategoryDTO: productCategoryDTOList){
                categoryToProductsMap.put(productCategoryDTO, productConverter.toProductDTOList(productRepository.search(connection, productCategoryDTO.getCategoryName(), null)));
            }
            return categoryToProductsMap;
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
