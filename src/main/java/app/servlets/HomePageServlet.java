package app.servlets;

import app.repositories.ProductCategoryRepository;
import app.repositories.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/homepage")
public class HomePageServlet extends HttpServlet {
    private ProductCategoryRepository productCategoryRepository = new ProductCategoryRepository();
    private ProductRepository productRepository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", productCategoryRepository.getAll());
        req.getRequestDispatcher("views/homepage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", productRepository.getByCategoryName((req.getParameter("chosenCategoryName"))));

        doGet(req, resp);
    }
}
