package app.servlets;

import app.repositories.BucketRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {
    private final BucketRepository bucketRepository = new BucketRepository();
    private final Logger logger = Logger.getLogger(LoginServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("bucket", bucketRepository.getBucketByUserId((Integer) req.getSession().getAttribute("customer_id")));
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Error while trying to get bucket items", e);
        }
        req.getRequestDispatcher("views/bucket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    }
}


