package app.servlets.admin.customer;

import app.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/customer/block")
public class CustomerBlockServlet extends HttpServlet {

    private static final String MANAGE_CUSTOMER_JSP = "/admin/customer/manageCustomer.jsp";
    private static final CustomerService customerService = new CustomerService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerIdToBlock = req.getParameter("customerIdToBlock");

        if (customerIdToBlock != null) {
            req.setAttribute("isStatusChanged", customerService.changeCustomerStatus(customerIdToBlock));
        }
        req.setAttribute("customers", customerService.getAll());
        req.getRequestDispatcher(MANAGE_CUSTOMER_JSP).forward(req, resp);
    }
}
