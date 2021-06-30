package app.servlets;

import app.entities.Customer;
import app.repositories.CustomerRepository;
import app.repositories.ProductCategoryRepository;
import app.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Customer customer = loginService.doLogin(login, password);

        if (customer != null) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("logged", true);
            req.getSession().setAttribute("customer_id", customer.getId());
            resp.sendRedirect(req.getContextPath() +"/homepage");
        } else {
            req.setAttribute("logged", false);
            doGet(req, resp);
        }

    }
}
