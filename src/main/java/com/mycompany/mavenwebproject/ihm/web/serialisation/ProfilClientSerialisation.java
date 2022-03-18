/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mycompany.tdmaven.metier.modele.Client;
import com.mycompany.tdmaven.metier.service.Service;
import java.io.IOException;
import java.io.PrintWriter;
//import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bbbbb
 */
public class ProfilClientSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        JsonObject jsonClient = new JsonObject(); //Creation un objet Json pour un client
        
        String mail = request.getParameter("mail");
        String mdp = request.getParameter("mdp");
        Service service = new Service();
        Client client = service.authentifierClient(mail, mdp);
        jsonClient.addProperty("id", client.getId());
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jsonClient, out);
        out.close();
    } 
}
