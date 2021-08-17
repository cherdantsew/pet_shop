package app.service;

import app.entities.Customer;
import app.exceptions.TransactionExecutionException;
import app.exceptions.ValidationException;
import app.repositories.CustomerRepository;
import app.repositories.TransactionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationService {

    private static final int MIN_AGE = 18;
    private final CustomerRepository customerRepository = new CustomerRepository();

    public boolean doRegistration(String login, String password, String name, Integer age) {
        try {
            System.err.println("Come to doRegistration");;
            TransactionHandler<Boolean> transactionHandler = new TransactionHandler<>((connection) -> {
                Customer customer = toCustomer(login, password, name, age);
                validate(customer, connection);
                if (customerRepository.getByLogin(connection, customer.getLogin()) != null) {
                    throw new ValidationException(String.format("Customer with login %s already exists", customer.getLogin()));
                }
                return customerRepository.insert(connection, customer);
            });
            return transactionHandler.execute();
        } catch (SQLException e) {
            throw new TransactionExecutionException(e);
        }
    }

    private void validate(Customer customer, Connection connection) throws SQLException {
        List<String> errors = new ArrayList<>();
        if (customer.getAge() < MIN_AGE) {
            errors.add("Customers under age 18 are not allowed.");
        }
        if (customerRepository.getByLogin(connection, customer.getLogin()) != null) {
            errors.add(String.format("Customer with login %s already exists", customer.getLogin()));
        }
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(StringUtils.join(errors, ","));
        }
    }

    private Customer toCustomer(String login, String password, String name, Integer age) {
        return Customer.builder()
                .login(login)
                .password(password)
                .name(name)
                .age(age)
                .build();
    }
}
