package app.repositories;

import app.exceptions.ConnectionInitializationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract boolean insert(T objectToInsert) throws SQLException;

    public abstract boolean update(T objectToUpdate) throws SQLException;

    public abstract T getById(int id) throws SQLException;

    public abstract boolean delete(T objectToDelete) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    protected Connection getConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            DataSource dataSource = (DataSource) envContext.lookup("jdbc/javashema");
            return dataSource.getConnection();
        } catch (SQLException | NamingException e) {
            throw new ConnectionInitializationException(e);
        }
    }
}
