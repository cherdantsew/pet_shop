package app.service;

import app.entities.Customer;
import app.exceptions.CustomerAlreadyExistsException;
import app.exceptions.TransactionExecutionException;
import app.repositories.CustomerRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;

public class RegistrationService {
    CustomerRepository customerRepository = new CustomerRepository();

    public boolean doRegistration(Customer customer) {
        TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>((connection) -> {
            if (customerRepository.getByLogin(connection, customer.getLogin()) != null) {
                throw new CustomerAlreadyExistsException("Customer with login %s already exists", customer.getLogin());
            }
            return customerRepository.insert(connection, customer);
        });
        try {
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }
}
