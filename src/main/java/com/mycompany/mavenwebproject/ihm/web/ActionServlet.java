/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web;

import com.mycompany.mavenwebproject.ihm.web.action.Action;
import com.mycompany.mavenwebproject.ihm.web.action.AuthentifierClientAction;
import com.mycompany.mavenwebproject.ihm.web.serialisation.ProfilClientSerialisation;
import com.mycompany.mavenwebproject.ihm.web.serialisation.Serialisation;
import com.mycompany.tdmaven.dao.JpaUtil;
import com.mycompany.tdmaven.metier.modele.Client;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bbbbb
 */
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    
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
        request.getSession(true);
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8"); 
        // Encodage de paramètre de la requete (pour
        // une lecture correcte
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"fr\">");
            out.println("<head>");
            out.println("<title>Servlet ActionServlet</title>");    
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActionServlet at " + request.getContextPath() + "</h1>");
         
            Action action = null;
            Serialisation serialisation = null;
            
            out.println("<form action=\"ActionServlet?todo=connecter\" method=\"POST\">");
            out.println("<label for=\"idUser\">Mail </label>");
            out.println("<input type=\"text\" id=\"mail\" name=\"mail\"/><br/>");
            out.println("<label for=\"password\">Mot de passe </label>");
            out.println("<input type=\"password\" id=\"mdp\" name=\"mdp\"/><br/>");
            out.println("<input type=\"submit\" value=\"Valider\"/>");
            out.println("</form>");
            
            String todo = request.getParameter("todo");
            if (todo.compareTo("connecter") == 0) {
                action = new AuthentifierClientAction();
                serialisation = new ProfilClientSerialisation();
            }
           
            if (action != null && serialisation != null) {
                action.executer(request);
                serialisation.serialiser(request, response);
            } else {
                response.sendError(400, "Bad Request");
            }
            out.println("</body>");
            out.println("</html>");
        }  
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

/*    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding('UTF-8');
    } */
    
}
