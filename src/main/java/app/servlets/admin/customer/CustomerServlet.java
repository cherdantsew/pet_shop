package app.servlets.admin.customer;

import app.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/customer")
public class CustomerServlet extends HttpServlet {
    private static final String MANAGE_CUSTOMER_JSP = "/admin/customer/manageCustomer.jsp";
    private  static final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("customers", customerService.getAll());
        req.getRequestDispatcher(MANAGE_CUSTOMER_JSP).forward(req, resp);
    }
}
