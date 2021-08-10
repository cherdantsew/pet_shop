package app.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/main")
public class AdminMainPageServlet extends HttpServlet {

    private static final String ADMIN_PAGE_JSP = "/admin/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }
}
