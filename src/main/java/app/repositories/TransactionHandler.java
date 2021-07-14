package app.repositories;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler<T>{
    Connection connection;
    TransactionHandlerInterface<T> transactionHandlerInterface;

    public TransactionHandler(Connection connection, TransactionHandlerInterface<T> transactionHandlerInterface) {
        this.connection = connection;
        this.transactionHandlerInterface = transactionHandlerInterface;
    }

    public T execute() throws SQLException {
        try {
            connection.setAutoCommit(false);
            T object = transactionHandlerInterface.run();
            connection.commit();
            return object;
        } catch (SQLException e){
            connection.rollback();
        }
        return null;
    }
}
