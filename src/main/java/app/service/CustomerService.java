package app.service;

import app.converters.CustomerConverter;
import app.dto.CustomerDTO;
import app.entities.Customer;
import app.exceptions.TransactionExecutionException;
import app.repositories.CustomerRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.List;

public class CustomerService {
    private static final CustomerRepository customerRepository = new CustomerRepository();
    private static final CustomerConverter customerConverter = new CustomerConverter();
    public List<CustomerDTO> getAll() {
        TransactionHandler<List<Customer>> transactionHandler = new TransactionHandler<>(customerRepository::getAll);
        try {
            return customerConverter.toCustomerDTOList(transactionHandler.execute());
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
