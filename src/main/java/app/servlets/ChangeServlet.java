package app.servlets;

import app.entities.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personal/change")
public class ChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/change.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        String name = req.getParameter("name");
        System.out.println("name " + name);

        String age = req.getParameter("age");
        System.out.println("age " + age);

        String password = req.getParameter("pass");
        System.out.println("pass " + password);


        req.setAttribute("changed", "true");
        doGet(req, resp);
    }
}
