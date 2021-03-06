package app.servlets;

import app.dto.CustomerDTO;
import app.entities.Customer;
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
    public static final String ADMIN_MAIN_PAGE = "/admin/main";
    public static final String CUSTOMER_HOMEPAGE = "/customer/homepage";
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
                if (!Customer.TYPE_BLOCKED.equals(customerDto.getIsBlocked()) && Customer.ROLE_ADMIN.equals(customerDto.getType())){
                    req.getSession().setAttribute("customer", customerDto);
                    resp.sendRedirect(req.getContextPath() + ADMIN_MAIN_PAGE);
                } else if (!Customer.TYPE_BLOCKED.equals(customerDto.getIsBlocked()) && Customer.ROLE_CUSTOMER.equals(customerDto.getType())){
                    req.getSession().setAttribute("customer", customerDto);
                    resp.sendRedirect(req.getContextPath() + CUSTOMER_HOMEPAGE);
                }
            }
        } catch (BadCredentialsException e) {
            logger.log(Level.WARNING, "Bad credentials while logging in. ");
            req.setAttribute("badCredentials", true);
            doGet(req, resp);
        }
    }
}
