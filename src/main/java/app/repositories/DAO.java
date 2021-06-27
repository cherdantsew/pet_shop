package app.repositories;

import app.exceptions.ConnectionInitializationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/javashema?characterEncoding=latin1";
    public static final String ROOT_LOGIN = "root";
    public static final String ROOT_PASSWORD = "root";

    public abstract boolean insert(T objectToInsert) throws SQLException;

    public abstract boolean update(T objectToUpdate) throws SQLException;

    public abstract T getById(int id) throws SQLException;

    public abstract boolean delete(T objectToDelete) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionInitializationException(e);
        }
    }
}
