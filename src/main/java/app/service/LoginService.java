package app.service;

import app.converters.CustomerConverter;
import app.dto.CustomerDTO;
import app.entities.Customer;
import app.exceptions.BadCredentialsException;
import app.exceptions.TransactionExecutionException;
import app.repositories.CustomerRepository;
import app.repositories.TransactionHandler;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class.getName());
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final CustomerConverter customerConverter = new CustomerConverter();

    public CustomerDTO doLogin(String login, String password) {
        try {
            TransactionHandler<Customer> transactionHandler = new TransactionHandler<>((connection) -> customerRepository.getByLogin(connection, login));
            Customer customer = transactionHandler.execute();
            if (customer != null && password.equals(customer.getPassword())) {
                return customerConverter.toDto(customer);
            } else {
                throw new BadCredentialsException(String.format("Invalid credentials: login:%s, password:%s", login, password));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "couldn't execute transaction in LoginService", e);
            throw new TransactionExecutionException(e);
        }
    }
}
