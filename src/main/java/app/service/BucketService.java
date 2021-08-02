package app.service;

import app.entities.Order;
import app.entities.Product;
import app.exceptions.TransactionExecutionException;
import app.repositories.CustomerRepository;
import app.repositories.OrderRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.Map;

public class BucketService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();
    public Map<Integer, Product> getBucket(int customerId) {
        try {
            TransactionHandler<Map<Integer, Product>> transactionHandler = new TransactionHandler<>((connection) -> customerRepository.getBucketProductsByCustomerId(connection, customerId));
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
