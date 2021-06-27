package app.servlets;

import app.entities.Order;
import app.repositories.BucketRepository;
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

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {

    private ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private ProductRepository productRepository = new ProductRepository();
    private OrderRepository orderRepository = new OrderRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", productCategoryRepository.getAll());
        req.getRequestDispatcher("views/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productRepository.getByCategoryName((req.getParameter("chosenCategoryName"))));
        System.out.println(req.getParameter("chosenCategoryName") + " chosen category name");
        System.out.println(req.getParameter("chosenProduct") + " its a chosen product");
        if (req.getParameter("chosenProduct") != null && req.getSession().getAttribute("logged") != null) {
            int customerId = (int) req.getSession().getAttribute("customer_id");
            Date date = new Date();
            String dateOfOrder = date.toString();
            int productId = Integer.parseInt(req.getParameter("chosenProduct"));
            String status = "IN_PROGRESS";
            Order order = new Order(customerId, dateOfOrder, productId, status);
            try {
                orderRepository.insert(order);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        doGet(req, resp);
    }
}
