package app.repositories;

import java.sql.Connection;
import java.sql.SQLException;

public interface TransactionHandlerInterface <T>{
    T run(Connection connection) throws SQLException;
}
