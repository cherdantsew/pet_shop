package app.servlets.admin;

import app.exceptions.ValidationException;
import app.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/manageCustomer")
public class CustomerManageServlet extends HttpServlet {
    public static final String ADMIN_PAGE_JSP = "/admin/adminPage.jsp";
    CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String customerIdToChangeStatus = req.getParameter("customerIdToBlock");
            String customerToAdmin = req.getParameter("customerIdToAdmin");
            if (customerIdToChangeStatus != null) {
                req.setAttribute("isStatusChanged", customerService.changeCustomerStatus(customerIdToChangeStatus));
            }
            if (customerToAdmin != null){
                req.setAttribute("isTypeChanged", customerService.changeCustomerType(customerToAdmin));
            }
        }catch (ValidationException e){
            req.setAttribute("isStatusChanged", false);
        }
        req.setAttribute("customers", customerService.getAll());
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }
}
