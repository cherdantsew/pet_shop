package app.repositories;

import java.sql.SQLException;

public interface TransactionHandlerInterface <T>{
    T run() throws SQLException;
}
