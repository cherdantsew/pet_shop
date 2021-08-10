package app.servlets.admin.category;

import app.exceptions.ValidationException;
import app.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/category/remove")
public class CategoryRemoveServlet extends HttpServlet {

    private static final CategoryService categoryService = new CategoryService();
    private static final String MANAGE_CATEGORY_JSP = "/admin/category/manageCategory.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String categoryIdToRemove = req.getParameter("categoryIdToRemove");
            if (categoryIdToRemove != null) {
                boolean isCategoryDeleted = categoryService.removeCategory(categoryIdToRemove);
                req.setAttribute("isCategoryDeleted", isCategoryDeleted);
            }
        } catch (ValidationException e){
            req.setAttribute("hasElements", true);
        }
        req.setAttribute("categories", categoryService.getAllCategories());
        req.getRequestDispatcher(MANAGE_CATEGORY_JSP).forward(req, resp);
    }
}
