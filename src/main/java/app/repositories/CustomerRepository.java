package app.repositories;

import app.entities.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends DAO {

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/javashema?characterEncoding=latin1";
    public static final String ROOT_LOGIN = "root";
    public static final String ROOT_PASSWORD = "root";

    @Override
    public boolean insert(Object objectToInsert) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Customer customerToInsert = (Customer) objectToInsert;

        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            String INSERT_URL = String.format("INSERT INTO customers (login, password, name, age) VALUES ('%s', '%s', '%s', '%d');",
                    customerToInsert.getLogin(),
                    customerToInsert.getPassword(),
                    customerToInsert.getName(),
                    customerToInsert.getAge());
            statement.executeUpdate(INSERT_URL);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


    @Override
    public void update(Object objectToUpdate) {
        Customer customerToUpdate = (Customer) objectToUpdate;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            String INSERT_URL = String.format("UPDATE customers SET login = '%s', password = '%s', name = '%s', age = %d;",
                    customerToUpdate.getLogin(),
                    customerToUpdate.getPassword(),
                    customerToUpdate.getName(),
                    customerToUpdate.getAge());
            statement.executeUpdate(INSERT_URL);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Object getById(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery("select * from customers where customer_id = " + id)) {
                while (resultSet.next()) {
                    return new Customer(id, resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getInt("age"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Object getByLoginAndPassword(String login, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery("select * from customers where login = " + login + " and password = " + password)) {
                while (resultSet.next()) {
                    return new Customer(resultSet.getInt("customer_id"), resultSet.getString("login"), resultSet.getString("password"), resultSet.getString("name"), resultSet.getInt("age"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void delete(Object objectToDelete) {
        Customer customerToDelete = (Customer) objectToDelete;

        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String DELETE_URL = ("DELETE FROM customers WHERE customer_id = " + customerToDelete.getId());
            statement.executeUpdate(DELETE_URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customersList = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            String GETALL_URL = ("SELECT * FROM customers;");

            ResultSet resultSet = statement.executeQuery(GETALL_URL);

            while (resultSet.next()) {
                customersList.add(new Customer(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getInt("age")));
            }

        } catch (SQLException e) {
            System.err.println(e);
            System.err.println("ERROR WHILE TRYING TO GET ALL USERS");
        }

        return customersList;
    }
}
