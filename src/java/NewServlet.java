/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.Document;

/**
 *
 * @author Moiz
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
private static int count=0;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");
            URL url = new URL("https://graph.facebook.com/oauth/access_token?client_id=655619321213160&redirect_uri=http://localhost:8080/FBServlet/newjsp1.jsp&client_secret=05ea276b310ec1d87470e289f4b73c81&code=" + code);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String s = null;
            /*while ((s = reader.readLine()) != null) {
                out.println(s);
            }*/
            s=s+reader.readLine();
            s=s+reader.readLine();
            s=s+reader.readLine();
            //s=s.substring(16);
            s=s.split("=")[1];
            s=s.split("&")[0];
            response.sendRedirect("newjsp2.jsp?access="+s);
            
            //String site = new String("https://graph.facebook.com/oauth/access_token?client_id=655619321213160&redirect_uri=http://localhost:8080/FBServlet/newjsp1.jsp&client_secret=05ea276b310ec1d87470e289f4b73c81&code=" + code);
            //response.setStatus(response.SC_MOVED_TEMPORARILY);
            //response.setHeader("Location", site);
            //out.println("<h1>"+code+"</h1>");
            //String access = request.getParameter("access_token");
            /*URL oracle = new URL("https://graph.facebook.com/oauth/access_token?client_id=655619321213160&redirect_uri=http://localhost:8080/FBServlet/newjsp1.jsp&client_secret=05ea276b310ec1d87470e289f4b73c81&code=" + code);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                inputLine+=inputLine;
            }
            in.close();*/
            //access=request.getHeader("Location");
            
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
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
     * @param request servlet request
     * @param response servlet response
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
