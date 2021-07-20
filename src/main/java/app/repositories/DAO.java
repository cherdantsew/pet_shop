package app.repositories;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {
    public abstract boolean insert(Connection connection, T objectToInsert) throws SQLException;

    public abstract boolean update(Connection connection, T objectToUpdate) throws SQLException;

    public abstract T getById(Connection connection, int id) throws SQLException;

    public abstract boolean delete(Connection connection, T objectToDelete) throws SQLException;

    public abstract List<T> getAll(Connection connection) throws SQLException;

}
