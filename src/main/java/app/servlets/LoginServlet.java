package app.servlets;

import app.service.LoginService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private  LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        boolean success = loginService.doLogin(login, password);

        if (success) {
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("logged", true);
            resp.sendRedirect("/homepage");
        } else {
            req.setAttribute("logged", false);
            doGet(req, resp);
        }

    }
}
