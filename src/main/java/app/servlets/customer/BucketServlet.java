package app.servlets.customer;

import app.dto.CustomerDTO;
import app.exceptions.TransactionExecutionException;
import app.service.BucketService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/customer/bucket")
public class BucketServlet extends HttpServlet {
    private final Logger logger = Logger.getLogger(BucketServlet.class.getName());
    BucketService bucketService = new BucketService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            CustomerDTO customerDTO = (CustomerDTO) req.getSession().getAttribute("customer");
            req.getSession().setAttribute("bucket", bucketService.getBucket(customerDTO.getId()));
        } catch (TransactionExecutionException e) {
            logger.log(Level.WARNING, "Error while performing transaction", e);
        }
        req.getRequestDispatcher("/customer/bucket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}


