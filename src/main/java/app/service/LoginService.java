package app.service;

import app.entities.Customer;
import app.repositories.CustomerRepository;

import java.sql.SQLException;
import java.util.List;

public class LoginService {

    private CustomerRepository customerRepository = new CustomerRepository();

    public boolean doLogin(String login, String password) {
        List<Customer> customersList = null;
        try {
            customersList = customerRepository.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Customer customer : customersList) {
            if (customer.getLogin().equals(login) && customer.getPassword().equals(password))
                return true;
        }
        return false;
    }


}
