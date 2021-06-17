package app.servlets;

import app.repositories.BucketRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bucket")
public class BucketServlet extends HttpServlet {
    private BucketRepository bucketRepository = new BucketRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("bucket", bucketRepository.getBucketByUserId((Integer) req.getSession().getAttribute("customer_id")));
        req.getRequestDispatcher("views/bucket.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}


