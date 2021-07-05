package app.servlets.customer;

import app.entities.Order;
import app.repositories.OrderRepository;
import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/homepage")
public class HomePageServlet extends HttpServlet {

    private final ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private final ProductRepository productRepository = new ProductRepository();
    private final OrderRepository orderRepository = new OrderRepository();
    private final Logger logger = Logger.getLogger(HomePageServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("categories", productCategoryRepository.getAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (req.getParameter("chosenCategoryName") != null) {
                req.getSession().setAttribute("products", productRepository.getByCategoryName((req.getParameter("chosenCategoryName"))));
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while getting list of products corresponding to a specific Category.", e);
        }

        if (req.getParameter("chosenProduct") != null && req.getSession().getAttribute("logged") != null) {
            int customerId = (int) req.getSession().getAttribute("customer_id");
            Date date = new Date();
            String dateOfOrder = date.toString();
            int productId = Integer.parseInt(req.getParameter("chosenProduct"));
            String status = "IN_PROGRESS";
            Order order = new Order(customerId, dateOfOrder, productId, status);
            try {
                boolean inserted = orderRepository.insert(order);
                if (inserted) {
                    req.setAttribute("addedToBucket", true);
                }
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error while adding a product to orders table", e);
            }
        }
        doGet(req, resp);
    }
}
