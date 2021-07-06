package app.servlets;

import app.repositories.CustomerRepository;
import app.entities.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = getCustomer(req);
        try {
            boolean isAdded = new CustomerRepository().insert(customer);
            if (isAdded) {
                req.setAttribute("isAdded", true);
                doGet(req, resp);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while trying to insert a new customer into database", e);
        }
    }

    private Customer getCustomer(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        return new Customer(login, password, name, age);
    }
}
