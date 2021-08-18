package app.repositories;

import app.exceptions.ConnectionInitializationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler<T> {

    private static final String JAVA_COMP_ENV = "java:comp/env";
    private static final String JDBC_JAVASHEMA = "jdbc/javashema";
    private final TransactionHandlerInterface<T> transactionHandlerInterface;

    public TransactionHandler(TransactionHandlerInterface<T> transactionHandlerInterface) {
        this.transactionHandlerInterface = transactionHandlerInterface;
    }

    public T execute() throws SQLException {
        try (Connection connection = getConnection()) {
            try {
                connection.setAutoCommit(false);
                T object = transactionHandlerInterface.run(connection);
                connection.commit();
                return object;
            } catch (SQLException e) {
                connection.rollback();
            }
            return null;
        }
    }

    private Connection getConnection() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(JAVA_COMP_ENV);
            DataSource dataSource = (DataSource) envContext.lookup(JDBC_JAVASHEMA);
            return dataSource.getConnection();
        } catch (SQLException | NamingException e) {///
            throw new ConnectionInitializationException(e);
        }
    }
}
