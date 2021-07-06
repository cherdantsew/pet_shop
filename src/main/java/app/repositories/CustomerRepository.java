package app.repositories;

import app.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends DAO<Customer> {

    public static final String INSERT_STATEMENT = "INSERT INTO customers (login, password, name, age) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_STATEMENT = "UPDATE customers SET login = ?, password = ?, name = ?, age = ? WHERE customer_id = ?";
    public static final String GET_BY_ID_STATEMENT = "SELECT * FROM customers WHERE customer_id = ?";
    public static final String SELECT_BY_LOGIN_AND_PASSWORD_STATEMENT = "SELECT * FROM customers WHERE login = ? AND password = ?";
    public static final String DELETE_CUSTOMER_BY_ID_STATEMENT = "DELETE FROM customers WHERE customer_id = ?";
    public static final String SELECT_ALL_FROM_CUSTOMERS_STATEMENT = "SELECT * FROM customers";
    private static final String SELECT_BY_LOGIN_STATEMENT = "SELECT * FROM customers WHERE login = ?";

    @Override
    public boolean insert(Customer customer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);
            statement.setString(1, customer.getLogin());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getName());
            statement.setInt(4, customer.getAge());
            return statement.executeUpdate() == 1;
        }
    }


    @Override
    public boolean update(Customer customer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT);
            statement.setString(1, customer.getLogin());
            statement.setString(2, customer.getPassword());
            statement.setString(3, customer.getName());
            statement.setInt(4, customer.getAge());
            statement.setInt(5, customer.getId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public Customer getById(int id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(GET_BY_ID_STATEMENT);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCustomer(resultSet);
                }
            }
        }
        return null;
    }

    public Customer getByLoginAndPassword(String login, String password) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_AND_PASSWORD_STATEMENT);
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapCustomer(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(Customer customer) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_STATEMENT);
            statement.setInt(1, customer.getId());
            return statement.executeUpdate() == 1;
        }
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> customersList = new ArrayList();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_CUSTOMERS_STATEMENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customersList.add(mapCustomer(resultSet));
            }
        }
        return customersList;
    }

    public Customer getByLogin(String login) throws SQLException{
        Customer customer = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN_STATEMENT);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return mapCustomer(resultSet);
            }
        }
        return null;
    }

    private Customer mapCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("customer_id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getInt("age"));
    }
}
