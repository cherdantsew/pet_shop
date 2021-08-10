package app.servlets.admin.product;

import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/product")
public class ProductServlet extends HttpServlet {

    private static final ProductSearchService productSearchService = new ProductSearchService();
    private static final String MANAGE_PRODUCT_JSP = "/admin/product/manageProduct.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categoryWithProductsMap", productSearchService.getCategoryWithProductsMap());
        req.getRequestDispatcher(MANAGE_PRODUCT_JSP).forward(req, resp);
    }
}
