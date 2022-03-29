/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
        
        //Lecture des Attributs de la requête (stockés par Action)
        Object connexion = request.getAttribute("connexion");
        Object id = request.getAttribute("id");
        Object nom = request.getAttribute("nom");
        Object prenom = request.getAttribute("prenom");
        Object mail = request.getAttribute("mail");
        
        //Ajouter des propriétés au objet jsonClient
        jsonClient.addProperty("connexion", connexion.toString());
        jsonClient.addProperty("id", id.toString());
        jsonClient.addProperty("nom", nom.toString());
        jsonClient.addProperty("prenom", prenom.toString());
        jsonClient.addProperty("mail", mail.toString());
        
        //out.println("<p>" + mail + "</p><br/>");
        //Formattage de la structure de données Json
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(jsonClient, out);
        out.close();
    } 
}
