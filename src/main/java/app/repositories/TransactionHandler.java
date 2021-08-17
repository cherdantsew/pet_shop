package app.repositories;

import app.exceptions.ConnectionInitializationException;

import java.net.URI;
import java.net.URISyntaxException;
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
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() throws SQLException, URISyntaxException {
        try {
            URI jdbUri = new URI(System.getenv("JAWSDB_URL"));

            String username = jdbUri.getUserInfo().split(":")[0];
            String password = jdbUri.getUserInfo().split(":")[1];
            String port = String.valueOf(jdbUri.getPort());
            String jdbUrl = "jdbc:mysql://" + jdbUri.getHost() + ":" + port + jdbUri.getPath();

            return DriverManager.getConnection(jdbUrl, username, password);
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //MysqlDataSource dataSource = new MysqlDataSource();
            //dataSource.setUrl("mysql://s0qk0z4uwrnzkpzm:g91j65w8drr78av3@pei17y9c5bpuh987.chr7pe7iynqr.eu-west-1.rds.amazonaws.com:3306/ladcrjtpdtas3gwj");
            //dataSource.setUser("s0qk0z4uwrnzkpzm");
            //dataSource.setPassword("g91j65w8drr78av3");
            //String dbUrl = System.getenv("JDBC_DATABASE_URL");
            //Context initContext = new InitialContext();
            //Context envContext = (Context) initContext.lookup(JAVA_COMP_ENV);
            //DataSource dataSource = (DataSource) envContext.lookup(JDBC_JAVASHEMA);
            //return dataSource.getConnection();
            /* return DriverManager.getConnection(dbUrl); */
            //return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConnectionInitializationException(e);
        }
    }
}
