package app.servlets.admin;

import app.exceptions.CategoryValidationException;
import app.exceptions.ProductValidationException;
import app.service.ProductAndCategoryManageService;
import app.service.ProductSearchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addProductsAndCategories")
public class AddProductsAndCategoriesServlet extends HttpServlet {

    private static final ProductAndCategoryManageService productAndCategoryManageService = new ProductAndCategoryManageService();
    private static final ProductSearchService productSearchService = new ProductSearchService();
    private static final String ADMIN_PAGE_JSP = "/admin/adminPage.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            addNewCategory(req);
            addNewProduct(req);
        } catch (CategoryValidationException e){
            req.setAttribute("categoryAlreadyExists", true);
        } catch (ProductValidationException e){
            req.setAttribute("productAlreadyExists", true);
        }

        req.setAttribute("categoryProductsMap", productSearchService.getCategoryWithProductsMap());
        req.getRequestDispatcher(ADMIN_PAGE_JSP).forward(req, resp);
    }

    private void addNewProduct(HttpServletRequest req) {
        String categoryId = req.getParameter("newProductCategoryId");
        String productName = req.getParameter("newProductName");
        String productPrice = req.getParameter("newProductPrice");
        String productDescription = req.getParameter("newProductDescription");
        req.setAttribute("isProductAdded", productAndCategoryManageService.addProduct(categoryId, productName, productPrice, productDescription));
    }

    private void addNewCategory(HttpServletRequest req) {
        String newCategoryName = req.getParameter("newCategoryName");
        if (newCategoryName != null) {
            req.setAttribute("isCategoryAdded", productAndCategoryManageService.addCategory(newCategoryName));
        }
    }
}