package app.servlets.admin;

import app.service.ProductAndCategoryManageService;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteProductsAndCategories")
public class DeleteProductsAndCategoriesServlet extends HttpServlet {
    private static final ProductAndCategoryManageService productAndCategoryManageService = new ProductAndCategoryManageService();
    private static final String ADMIN_PAGE_JSP = "/admin/adminPage.jsp";
    private static final ProductSearchService productSearchService = new ProductSearchService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryNameToDelete = req.getParameter("categoryNameToDelete");
        String productIdToDelete = req.getParameter("productIdToDelete");
        if (categoryNameToDelete != null){
            boolean isCategoryDeleted = productAndCategoryManageService.deleteCategory(categoryNameToDelete);
            req.setAttribute("isCategoryDeleted", isCategoryDeleted);
        }
        if (productIdToDelete != null){
            boolean isProductDeleted = productAndCategoryManageService.deleteProduct(Integer.valueOf(productIdToDelete));
            req.setAttribute("isProductDeleted", isProductDeleted);
        }
        req.setAttribute("categoryProductsMap", productSearchService.getCategoryWithProductsMap());
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }
}
