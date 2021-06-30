package app.service;

import app.entities.Customer;
import app.exceptions.CustomerNotFountException;
import app.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class LoginService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public Customer doLogin(String login, String password) {
        try {
            List<Customer> customersList = customerRepository.getAll();

            Customer customer = findUserByLogin(customersList, login);
            if (customer != null && password.equals(customer.getPassword())) {
                return customer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new CustomerNotFountException(new RuntimeException());
    }

    private Customer findUserByLogin(List<Customer> customerList, String login) {
        for (Customer customer : customerList) {
            if (login.equals(customer.getLogin()))
                return customer;
        }
        return null;
    }

}
