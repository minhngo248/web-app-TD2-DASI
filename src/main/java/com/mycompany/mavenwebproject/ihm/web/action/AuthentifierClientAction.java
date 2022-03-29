/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web.action;

import com.mycompany.tdmaven.metier.modele.Client;
import com.mycompany.tdmaven.metier.service.Service;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bbbbb
 */
public class AuthentifierClientAction extends Action { //Heritage de la classe abstracte Action

    /**
     *
     * @param request 
     */

    @Override
    public void executer(HttpServletRequest request) {
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        Service service = new Service();
        Client client = service.authentifierClient(mail, mdp);
        
        if (client != null) {
            request.setAttribute("connexion", "true");
            request.setAttribute("id", client.getId());
            request.setAttribute("nom", client.getNom());
            request.setAttribute("prenom", client.getPrenom());
            request.setAttribute("mail", mail);
        }
    }
}
