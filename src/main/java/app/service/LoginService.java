package app.service;

import app.entities.Customer;
import app.exceptions.CustomerNotFountException;
import app.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class LoginService {

    private final Logger logger = Logger.getLogger(LoginService.class.getName());
    private final CustomerRepository customerRepository = new CustomerRepository();

    public Customer doLogin(String login, String password) {
        try {
            List<Customer> customersList = customerRepository.getAll();
            for (Customer customer : customersList) {
                if (login.equals(customer.getLogin())) {
                    if (password.equals(customer.getPassword()))
                        return customer;
                }
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        logger.warning(String.format("User wasn't found. Credentials: login:%s, password: %s", login, password));
        throw new CustomerNotFountException(login, password);
    }
}
