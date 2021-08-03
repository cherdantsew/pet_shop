package app.servlets;

import app.exceptions.ValidationException;
import app.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final String REGISTER_JSP = "/register.jsp";
    private final Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    private final RegistrationService registrationService = new RegistrationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        try {
            if (registrationService.doRegistration(login, password, name, age)) {
                req.setAttribute("isAdded", true);
            }
        } catch (ValidationException e) {
            req.setAttribute("loginAlreadyTaken", true);
        }
        req.getRequestDispatcher(REGISTER_JSP).forward(req, resp);
    }

}
