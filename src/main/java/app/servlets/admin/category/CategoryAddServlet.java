package app.servlets.admin.category;

import app.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category/add")
public class CategoryAddServlet extends HttpServlet {

    private static final CategoryService categoryService = new CategoryService();
    private static final String MANAGE_CATEGORY_JSP = "/admin/category/manageCategory.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newCategoryName = req.getParameter("newCategoryName");
        if (newCategoryName != null) {
            req.setAttribute("isCategoryAdded", categoryService.addCategory(newCategoryName));
        }
        req.setAttribute("categories", categoryService.getAllCategories());
        req.getRequestDispatcher(MANAGE_CATEGORY_JSP).forward(req, resp);
    }
}
