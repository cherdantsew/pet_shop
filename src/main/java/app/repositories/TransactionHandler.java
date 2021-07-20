package app.repositories;

import app.exceptions.
         ConnectionInitializationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler<T> {
    Connection connection = getConnection();
    TransactionHandlerInterface<T> transactionHandlerInterface;

    public TransactionHandler(TransactionHandlerInterface<T> transactionHandlerInterface) {
        this.transactionHandlerInterface = transactionHandlerInterface;
    }

    public T execute() throws SQLException {
        try {
            boolean autocommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            T object = transactionHandlerInterface.run(connection);
            connection.commit();
            connection.setAutoCommit(autocommit);
            return object;
        } catch (SQLException e) {
            connection.rollback();
        }
        return null;
    }

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
