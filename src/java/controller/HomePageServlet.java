/*
 *Copyright(C) 2021,  FPTU.
 * J3.L.P0004 :
 *  Digital News
 *
 * Record of change:
 * DATE                       Version             AUTHOR            DESCRIPTION
 * 2021-05-10                  1.0                PhatNT         Start implement
 * 2021-05-12                  1.0                PhatNT         Test, Comment
 * 2021-05-24                  1.0                PhatNT         Review
 * 2021-05-31                  1.0                PhatNT         Fix bug, Fix comment
 */
package controller;

import dao.DigitalDAO;
import entity.Digital;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.impl.DigitalDAOImpl;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP POST
 * method, handles the Request of home page. The method will throw objects
 * <code>javax.servlet.ServletException</code> and
 * <code>java.io.IOException</code> class if there is any error occurring when
 * finding data or connect
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
@WebServlet(name = "HomePageServlet", urlPatterns = {"/LoadHomePage"})
public class HomePageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP GETand POST methods to show home page.
     * Receive and process requests on the home page. Use DigitalDAOImpl class
     * to query data and "HomePage" file jsp to forward results. Throw the
     * ServletException and IOException class if there is any error occurring
     * when finding data or connect
     *
     * @param request is a request from client to server. Extends the
     * ServletRequest interface to provide request information for HTTP server.
     * It's a <code>javax.servlet.http.HttpServletRequest</code>;
     * @param response Extends the ServletResponse interface to provide
     * HTTP-specific functionality in sending a response from server to client.
     * It's a <code>javax.servlet.http.HttpServletResponse</code>;
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request Extends the ServletRequest interface to provide request
     * information for HTTP servlets.
     * <code>javax.servlet.http.HttpServletRequest</code>
     * @param response Extends the ServletResponse interface to provide
     * HTTP-specific functionality in sending a response. For example, it has
     * methods to access HTTP headers and cookies.
     * <code>javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        try {
            //get the most recent digital
            DigitalDAO digitalDAO = new DigitalDAOImpl();
            //get the top 6 of the latest digitals
            List<Digital> top6RecentDigitals = digitalDAO.getTopDigital(6);
            //get short description of most recent new
            String shortDescription = top6RecentDigitals.get(0).getShortDes();
            //set most recent digital into request 
            request.setAttribute("mostRecentNew", top6RecentDigitals.get(0));
            //get top 5 next news have time post is most recent
            top6RecentDigitals.remove(0);
            request.setAttribute("top5MostRecentNew", top6RecentDigitals);
            //set short description into request
            request.setAttribute("shortDes", shortDescription);
            request.getRequestDispatcher("HomePage.jsp").include(request, response);
        } catch (Exception ex) {
            request.setAttribute("exceptionMessage", "An error occurred while trying to access");
            request.getRequestDispatcher("Error.jsp").include(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request Extends the ServletRequest interface to provide request
     * information for HTTP servlets.
     * <code>javax.servlet.http.HttpServletRequest</code>
     * @param response Extends the ServletResponse interface to provide
     * HTTP-specific functionality in sending a response. For example, it has
     * methods to access HTTP headers and cookies.
     * <code>javax.servlet.http.HttpServletResponse</code>
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
