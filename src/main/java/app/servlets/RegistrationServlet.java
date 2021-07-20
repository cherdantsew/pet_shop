package app.servlets;

import app.entities.Customer;
import app.exceptions.CustomerAlreadyExistsException;
import app.exceptions.TransactionExecutionException;
import app.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    private final RegistrationService registrationService = new RegistrationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = getCustomer(req);
        try {
            if (registrationService.doRegistration(customer)) {
                req.setAttribute("isAdded", true);
            }
        } catch (CustomerAlreadyExistsException e) {
            req.setAttribute("loginAlreadyTaken", true);
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Error while executing transaction in RegistrationService class.", e);
        }
        doGet(req, resp);
    }

    private Customer getCustomer(HttpServletRequest req) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        return new Customer(login, password, name, age);
    }
}
