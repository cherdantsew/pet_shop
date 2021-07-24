package app.repositories;

import app.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends DAO<Customer> {

    public static final String INSERT_STATEMENT = "INSERT INTO customers (login, password, name, age) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_STATEMENT = "UPDATE customers SET login = ?, password = ?, name = ?, age = ? WHERE customer_id = ?";
    public static final String GET_BY_ID_STATEMENT = "SELECT * FROM customers WHERE customer_id = ?";
    public static final String DELETE_CUSTOMER_BY_ID_STATEMENT = "DELETE FROM customers WHERE customer_id = ?";
    public static final String SELECT_ALL_FROM_CUSTOMERS_STATEMENT = "SELECT * FROM customers";
    private static final String SELECT_BY_LOGIN_STATEMENT = "SELECT * FROM customers WHERE login = ?";

    @Override
    public boolean insert(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT);
        preparedStatement.setString(1, customer.getLogin());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setInt(4, customer.getAge());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public boolean update(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT);
        preparedStatement.setString(1, customer.getLogin());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.setInt(4, customer.getAge());
        preparedStatement.setInt(5, customer.getId());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public Customer getById(Connection connection, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_STATEMENT);
        preparedStatement.setInt(1, id);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return mapCustomer(resultSet);
            }
        }
        return null;
    }

    @Override
    public boolean delete(Connection connection, Customer customer) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_BY_ID_STATEMENT);
        preparedStatement.setInt(1, customer.getId());
        return preparedStatement.executeUpdate() == 1;
    }

    @Override
    public List<Customer> getAll(Connection connection) throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_CUSTOMERS_STATEMENT);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                customersList.add(mapCustomer(resultSet));
            }
            return customersList;
        }
    }

    public Customer getByLogin(Connection connection, String login) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_LOGIN_STATEMENT);
        preparedStatement.setString(1, login);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
