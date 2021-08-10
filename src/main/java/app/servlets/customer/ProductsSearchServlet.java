package app.servlets.customer;

import app.dto.ProductDTO;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/homepage/products/search")
public class ProductsSearchServlet extends HttpServlet {

    private final static String HOMEPAGE_JSP = "/customer/homepage.jsp";
    private final static Logger logger = Logger.getLogger(ProductsSearchServlet.class.getName());

    private final ProductSearchService productSearchService = new ProductSearchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String chosenCategoryName = req.getParameter("chosenCategoryName");
            String productNamePrefix = req.getParameter("productNamePrefix");
            List<ProductDTO> productDTOList = productSearchService.search(chosenCategoryName, productNamePrefix.equals("") ? null : productNamePrefix);
            req.setAttribute("products", productDTOList);
            req.setAttribute("categories", productSearchService.getProductCategories());
            req.getRequestDispatcher(HOMEPAGE_JSP).forward(req, resp);
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Couldn't execute transaction (getting list of products related to a customer request.");
        }
    }
}
