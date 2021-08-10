package app.servlets.exception.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static javax.servlet.RequestDispatcher.ERROR_EXCEPTION;

@WebServlet("/exceptionHandler")
public class ExceptionHandlerServlet extends HttpServlet {
    public static final String EXCEPTION_HANDLER_EXCEPTION_INFO_JSP = "/exceptionHandler/exceptionInfo.jsp";
    private static final Logger logger = Logger.getLogger(ExceptionHandlerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exception exception = (Exception) req.getAttribute(ERROR_EXCEPTION);
        req.setAttribute("exceptionMessage", exception.getMessage());

        req.getRequestDispatcher(EXCEPTION_HANDLER_EXCEPTION_INFO_JSP).forward(req, resp);
    }
}
