package app.service;

import app.converters.CustomerConverter;
import app.dto.CustomerDTO;
import app.entities.Customer;
import app.exceptions.CustomerNotFountException;
import app.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class.getName());
    private final CustomerRepository customerRepository = new CustomerRepository();
    private final CustomerConverter customerConverter = new CustomerConverter();

    public CustomerDTO doLogin(String login, String password) {
        try {
            Customer customer = customerRepository.getByLogin(login);
            if (customer != null && password.equals(customer.getPassword()))
                return customerConverter.toDto(customer);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "couldn't establish connection to database while getting customer by login", e);
        }
        logger.warning(String.format("User wasn't found. Credentials: login:%s, password: %s", login, password));
        throw new CustomerNotFountException(login, password);
    }
}
