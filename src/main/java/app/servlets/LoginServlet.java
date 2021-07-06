package app.servlets;

import app.dto.CustomerDTO;
import app.entities.Customer;
import app.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        CustomerDTO customerDto = loginService.doLogin(login, password);
        if (customerDto != null) {
            req.getSession().setAttribute("customer", customerDto);
            resp.sendRedirect(req.getContextPath() + "/customer/homepage");
            return;
        }
        doGet(req, resp);
    }
}
