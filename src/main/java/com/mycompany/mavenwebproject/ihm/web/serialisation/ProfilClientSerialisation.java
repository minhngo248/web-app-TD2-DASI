/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenwebproject.ihm.web.serialisation;

import java.io.IOException;
import javax.json.JsonObject;
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
        JsonObject container = new JsonObject(); //Objet "conteneur Json" pour structurer les donnees a serialiser
        
        
    } 
}
