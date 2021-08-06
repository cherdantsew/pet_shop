package app.servlets.admin;

import app.exceptions.ValidationException;
import app.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/blockCustomer")
public class CustomerBlockServlet extends HttpServlet {
    public static final String ADMIN_PAGE_JSP = "/admin/adminPage.jsp";
    CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String customerId = req.getParameter("customerIdToBlock");
            if (customerId != null) {
                req.setAttribute("isStatusChanged", customerService.changeCustomerStatus(customerId));
            }
        }catch (ValidationException e){
            req.setAttribute("isStatusChanged", false);
        }
        req.setAttribute("customers", customerService.getAll());
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }
}
