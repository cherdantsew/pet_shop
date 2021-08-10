package app.service;

import app.entities.Product;
import app.exceptions.CategoryValidationException;
import app.exceptions.ProductValidationException;
import app.exceptions.TransactionExecutionException;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;
import app.repositories.TransactionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final ProductRepository productRepository = new ProductRepository();
    private static final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();

    public boolean removeProduct(Integer productIdToDelete) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> productRepository.deleteById(connection, productIdToDelete));
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    public boolean addProduct(String categoryId, String productName, String productPrice, String productDescription) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> {
            Product product = toProduct(categoryId, productName, productPrice, productDescription);
            if (validate(product)) {
                if (productRepository.getByNameAndCategoryId(connection, productName, Integer.parseInt(categoryId)) != null) {
                    throw new ProductValidationException(String.format("Product with name %s already exists in that category", productName));
                }
                if (productCategoryRepository.getById(connection, Integer.parseInt(product.getProductCategory())) != null){
                    throw new CategoryValidationException(String.format("category with id %s doesnt exist", categoryId));
                }
                return productRepository.insert(connection, product);
            }
            return false;
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    private Product toProduct(String categoryId, String productName, String productPrice, String productDescription) {
        return Product.builder()
                .productName(productName)
                .productPrice(productPrice)
                .productDescription(productDescription)
                .productCategory(String.valueOf(categoryId)).build();
    }

    private boolean validate(Product product) {
        List<String> errorList = new ArrayList<>();
        if (Integer.parseInt(product.getProductPrice()) < 10)
            errorList.add("Product is too cheap");
        if (StringUtils.isEmpty(product.getProductName()))
            errorList.add("Name is either blank or null");
        if (StringUtils.isEmpty(product.getProductDescription()))
            errorList.add("Description is empty");
        return CollectionUtils.isEmpty(errorList);
    }
}
