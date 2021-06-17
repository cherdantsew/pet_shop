package app.repositories;

import app.entities.Customer;
import app.entities.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class OrderRepository extends DAO {

    public static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/javashema?characterEncoding=latin1";
    public static final String ROOT_LOGIN = "root";
    public static final String ROOT_PASSWORD = "root";

    @Override
    public boolean insert(Object objectToInsert) {

        Order order = (Order) objectToInsert;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try (Connection connection = DriverManager.getConnection(JDBC_MYSQL_URL, ROOT_LOGIN, ROOT_PASSWORD)) {

            Statement statement = connection.createStatement();
            String INSERT_URL = String.format("INSERT INTO orders (customer_id, order_date, product_id, status)" +
                            "VALUES (%d, sysdate(), %d, '%s');",
                    order.getCustomer_id(),
                    order.getProduct_id(),
                    order.getStatus());
            statement.executeUpdate(INSERT_URL);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;    }

    @Override
    public void update(Object objectToUpdate) {

    }

    @Override
    public Object getById(int id) {
        return null;
    }

    @Override
    public void delete(Object objectToDelete) {

    }

    @Override
    public List getAll() {
        return null;
    }
}
