package app.service;

import app.entities.Order;
import app.entities.Product;
import app.exceptions.TransactionExecutionException;
import app.repositories.OrderRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class BucketService {
    private final OrderRepository orderRepository = new OrderRepository();
    public List<Product> getBucket(int customerId) {
        try {
            TransactionHandler<List<Product>> transactionHandler = new TransactionHandler<>((connection) -> orderRepository.getBucketProductsByCustomerId(connection, customerId));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
    public boolean addBucketItem(Order order) {
        try {
            TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>(connection -> orderRepository.insert(connection, order));
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
