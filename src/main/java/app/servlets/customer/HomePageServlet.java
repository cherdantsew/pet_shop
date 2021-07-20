package app.servlets.customer;

import app.dto.CustomerDTO;
import app.entities.Order;
import app.entities.Product;
import app.exceptions.TransactionExecutionException;
import app.service.HomePageService;

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
    private final HomePageService homePageService = new HomePageService();

    private final Logger logger = Logger.getLogger(HomePageServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("categories", homePageService.getProductCategories());
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Couldn't perform transaction (getting product categories).");
        }
        req.getRequestDispatcher("/customer/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("chosenCategoryName") != null) {
                List<Product> productList = homePageService.getProductsByCategoryName(req.getParameter("chosenCategoryName"));
                req.getSession().setAttribute("products", productList);
            }
            if (req.getParameter("chosenProduct") != null) {
                Order order = createOrder(req);
                if (homePageService.addBucketItem(order)) {
                    req.setAttribute("addedToBucket", true);
                }
            }
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Couldnt perform transaction (getting products by category name", e);
        }
        doGet(req, resp);
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
