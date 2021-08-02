package app.servlets.customer;

import app.dto.ProductDTO;
import app.exceptions.TransactionExecutionException;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/homepage/products/search")
public class ProductsSearchServlet extends HttpServlet {

    private final ProductSearchService productSearchService = new ProductSearchService();
    Logger logger = Logger.getLogger(ProductsSearchServlet.class.getName());
    private static final String HOMEPAGE_JSP = "/customer/homepage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("popal v doget");
            String chosenCategoryName = req.getParameter("chosenCategoryName");
            String productNamePrefix = req.getParameter("productNamePrefix");
            List<ProductDTO> productDTOList = productSearchService.handleSearchRequest(chosenCategoryName, productNamePrefix);
            req.setAttribute("products", productDTOList);
            req.setAttribute("categories", productSearchService.getProductCategories());
            req.getRequestDispatcher(HOMEPAGE_JSP).forward(req, resp);
        } catch (TransactionExecutionException e){
            logger.log(Level.WARNING, "Couldn't execute transaction (getting list of products related to a customer request.");
        }
    }
}
