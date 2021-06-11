package app.servlets;

import app.repositories.CustomerRepository;
import app.entities.Customer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personal/register")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.valueOf(req.getParameter("age"));

        Customer customer = new Customer(login, password, name, age);
        boolean isAdded = new CustomerRepository().insert(customer);

        if (isAdded)
            req.setAttribute("isAdded", isAdded);

        doGet(req, resp);
    }
}
