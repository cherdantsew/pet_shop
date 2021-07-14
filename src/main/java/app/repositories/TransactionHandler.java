package app.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHandler {

    public long handleUpdateTransaction(PreparedStatement preparedStatement, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
            return preparedStatement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return preparedStatement.getUpdateCount();
    }

    public ResultSet handleQueryTransaction(PreparedStatement preparedStatement, Connection connection) throws SQLException {
        try {
            connection.setAutoCommit(false);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return null;
    }
}
