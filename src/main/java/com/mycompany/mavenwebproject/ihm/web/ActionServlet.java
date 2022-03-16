/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web;

import com.mycompany.tdmaven.dao.JpaUtil;
import com.mycompany.mavenwebproject.ihm.web.action.Action;
import com.mycompany.mavenwebproject.ihm.web.action.AuthentifierClientAction;
import com.mycompany.mavenwebproject.ihm.web.serialisation.Serialisation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bbbbb
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
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
        
    }

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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession(true); //initialisation de la Session
        request.setCharacterEncoding("UTF-8"); //Encodage de paramètre de la requete (pour
                                                // une lecture correcte
        String todo = request.getParameter("todo");
        
        Action action = null;
        Serialisation serialisation = null;
        
        switch(todo) {
            case "connecter": {
                action = new AuthentifierClientAction();
                //serialisation = new ProfilClientSerialisation();
            }
            break;
            
            /*case "consulter-clients": {
                action = new ListerClientsAction();
                serialisation = new ListeClientsSerialisation();
            }
            break; */
        }
        if (action != null /*&& serialisation != null*/) {
            action.executer(request); // Executer l'Action
            //serialisation.serialiser(request, response); // Serialiser le resultat de l'Action
        } else { //Erreur : pas d'Action ou de Serialisation pour traiter cette requete
            response.sendError(400, "Bad request (pas d'Action ou de Serialisation pour "
                    + "traiter cette requete)");
        }
    }
}
