package app.servlets.customer;

import app.dto.CustomerDTO;
import app.dto.ProductDTO;
import app.entities.Order;
import app.exceptions.TransactionExecutionException;
import app.service.BucketService;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/homepage")
public class HomePageServlet extends HttpServlet {

    private static final String HOMEPAGE_JSP = "/customer/homepage.jsp";
    private final BucketService bucketService = new BucketService();
    private final ProductSearchService productSearchService = new ProductSearchService();
    private final Logger logger = Logger.getLogger(HomePageServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("categories", productSearchService.getProductCategories());
            req.setAttribute("products", productSearchService.getAllProducts());
            req.getRequestDispatcher(HOMEPAGE_JSP).forward(req, resp);
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Couldn't perform transaction (getting product categories).");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String chosenCategoryName = req.getParameter("chosenCategoryName");
            String productNamePrefix = req.getParameter("productNamePrefix");
            String chosenProductToBucket = req.getParameter("chosenProduct");
            if (chosenCategoryName != null) {
                List<ProductDTO> productList = productSearchService.getProductsByCategoryName(chosenCategoryName);
                req.setAttribute("products", productList);
            }
            if (productNamePrefix != null) {
                List<ProductDTO> prefixProductList = productSearchService.getProductsByNamePrefix(productNamePrefix);
                req.setAttribute("products", prefixProductList);
            }
            if (chosenProductToBucket != null) {
                Order order = createOrder(req);
                if (bucketService.addBucketItem(order)) {
                    req.setAttribute("addedToBucket", true);
                }
            }
            req.setAttribute("categories", productSearchService.getProductCategories());
            req.getRequestDispatcher(HOMEPAGE_JSP).forward(req, resp);
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Couldn't perform transaction (getting products by category name", e);
            doGet(req, resp);
        }
    }

    private Order createOrder(HttpServletRequest req) {
        CustomerDTO customerDTO = (CustomerDTO) req.getSession().getAttribute("customer");
        int customerId = customerDTO.getId();
        Date date = new Date();
        String dateOfOrder = date.toString();
        int productId = Integer.parseInt(req.getParameter("chosenProduct"));
        String status = "IN_PROGRESS";
        return new Order(customerId, dateOfOrder, productId, status);
    }
}
