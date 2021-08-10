package app.servlets.admin.category;

import app.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category")
public class CategoryServlet extends HttpServlet {

    private static final String MANAGE_CATEGORY_JSP = "/admin/category/manageCategory.jsp";
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.getAllCategories());
        req.getRequestDispatcher(MANAGE_CATEGORY_JSP).forward(req, resp);
    }
}
