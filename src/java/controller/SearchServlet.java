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
import java.util.ArrayList;

/**
 * The class contains method handles the HTTP GET method, handles the HTTP OST
 * method, handles the Request of search page.
 *
 * <p>
 * Bugs: None
 *
 * @author PhatNT
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchResult"})
public class SearchServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP GET and POST methods to show result
     * search page. Receive and process requests on the search page; Use
     * DigitalDAOImpl class to query data and "SearchRessultPage" file jsp to
     * forward results. Throw the ServletException and IOException class if
     * there is any error occurring when finding data or connect
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
        try {
            DigitalDAO digitalDAO = new DigitalDAOImpl();
            //get the top of the latest digitals
            List<Digital> top6RecentDigitals = digitalDAO.getTopDigital(6);

            //begin of get txtSearch and pageRow
            String txtSearch = request.getParameter("txtSearch").trim();
            String indexPageFromRequest = request.getParameter("txtPage");
            request.setAttribute("textSearch", txtSearch);
            indexPageFromRequest = (indexPageFromRequest == null)
                    ? "1" : indexPageFromRequest;
            int pageIndex = 0;
            //case control when index page is not numeric
            try {
                pageIndex = Integer.parseInt(indexPageFromRequest);
            } catch (Exception e) {
                pageIndex = -1;
            }
            //end of get txtSearch and pageRow

            //number of digital on a page
            int pageSize = 3;
            //number of result search
            int numberOfSearchResult = digitalDAO.getNumberOfSearchResult(txtSearch);
            //maximum of page index
            int maxPage = numberOfSearchResult / pageSize
                    + (numberOfSearchResult % pageSize > 0 ? 1 : 0);
            //page index existed then get corresponding degital else return massage
            if (pageIndex <= maxPage && pageIndex != -1) {
                List<Digital> listResultSearch = digitalDAO.
                        searchByTitleAndPagging(pageIndex, pageSize, txtSearch);
                for (Digital digital : listResultSearch) {
                    String title = digital.getTitle().toUpperCase();
                    String titleRoot = digital.getTitle();
                    String result = digital.getTitle();
                    List<String> listTxtSearch = new ArrayList<>();
                    while (title.contains(txtSearch.toUpperCase())) {
                        int indexStart = title.indexOf(txtSearch.toUpperCase());
                        int indexEnd = indexStart + txtSearch.length();
                        String convertText = titleRoot.substring(indexStart, indexEnd);
                        if(!listTxtSearch.contains(convertText)){
                            listTxtSearch.add(convertText);
                        }
                        title = title.substring(indexEnd);
                        titleRoot = titleRoot.substring(indexEnd);
                    }
                    for (String string : listTxtSearch) {
                        result = result.replaceAll(string, "<span class='highlightText'>" + string + "</span>");
                    }

                    digital.setTitle(result);
                }
                request.setAttribute("listResultSearch", listResultSearch);
                request.setAttribute("maxPage", maxPage);
            } else {
                request.setAttribute("message", "This page not found");
            }

            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("shortDes", top6RecentDigitals.get(0)
                    .getShortDes());
            //store text search
            request.setAttribute("txtSearch", txtSearch);
            //get the most recent digital
            request.setAttribute("mostRecentNew", top6RecentDigitals.get(0));
            //store number of results search
            request.setAttribute("numberOfResult", numberOfSearchResult);
            //get top5 next news have time post is most recent
            top6RecentDigitals.remove(0);
            request.setAttribute("top5MostRecentNew", top6RecentDigitals);
            //send data for search page
            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("exceptionMessage", "An error occurred while searching ");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
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
