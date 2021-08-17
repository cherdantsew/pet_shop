package app.repositories;

import app.exceptions.ConnectionInitializationException;

import java.sql.Connection;
import java.sql.DriverManager;
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

    private Connection getConnection() throws SQLException {
        try {
            //String dbUrl = System.getenv("JDBC_DATABASE_URL");
            //Context initContext = new InitialContext();
            //Context envContext = (Context) initContext.lookup(JAVA_COMP_ENV);
            //DataSource dataSource = (DataSource) envContext.lookup(JDBC_JAVASHEMA);
            //return dataSource.getConnection();
            /* return DriverManager.getConnection(dbUrl); */
            return DriverManager.getConnection("mysql://s0qk0z4uwrnzkpzm:g91j65w8drr78av3@pei17y9c5bpuh987.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ladcrjtpdtas3gwj", "s0qk0z4uwrnzkpzm", "g91j65w8drr78av3");
        } catch (SQLException e) {
            throw new ConnectionInitializationException(e);
        }
    }
}
