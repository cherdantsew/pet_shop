package app.servlets;

import app.dto.CustomerDTO;
import app.exceptions.BadCredentialsException;
import app.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public static final String LOGIN_JSP = "login.jsp";
    private final LoginService loginService = new LoginService();
    private final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            CustomerDTO customerDto = loginService.doLogin(login, password);
            if (customerDto != null) {
                req.getSession().setAttribute("customer", customerDto);
                resp.sendRedirect(req.getContextPath() + "/customer/homepage");
            }
        } catch (BadCredentialsException e) {
            logger.log(Level.WARNING, "Bad credentials while logging in. ");
            req.setAttribute("badCredentials", true);
            doGet(req, resp);
        }
    }
}
