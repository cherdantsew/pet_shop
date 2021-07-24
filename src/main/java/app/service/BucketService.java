package app.service;

import app.entities.Product;
import app.exceptions.TransactionExecutionException;
import app.repositories.BucketRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class BucketService {
    private final BucketRepository bucketRepository = new BucketRepository();

    public List<Product> getBucket(int customerId) {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>((connection) -> bucketRepository.getBucketByCustomerId(connection, customerId));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
