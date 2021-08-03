package app.servlets.admin;

import app.service.CustomerService;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/adminPage")
public class AdminPageServlet extends HttpServlet {

    private static final String ADMIN_PAGE_JSP = "/admin/adminPage.jsp";
    private static final ProductSearchService productSearchService = new ProductSearchService();
    private static final CustomerService customerService = new CustomerService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminAction = req.getParameter("adminAction");
        System.out.println(adminAction);
        switch (adminAction) {
            case "Manage categories and products":
                req.setAttribute("categories", productSearchService.getProductCategories());
                req.setAttribute("products", productSearchService.getAllProducts());
                break;
            case "Block customer":
                req.setAttribute("customers", customerService.getAll());
                break;
        }
        doGet(req, resp);
    }
}
