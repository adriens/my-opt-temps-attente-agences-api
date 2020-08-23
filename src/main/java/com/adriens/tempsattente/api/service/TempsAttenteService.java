/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriens.tempsattente.api.service;

import com.github.adriens.opt.tempsattente.sdk.Agence;
import com.github.adriens.opt.tempsattente.sdk.Agences;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author meilie
 */

@Service
public class TempsAttenteService {
    
    private final Logger log = LoggerFactory.getLogger(TempsAttenteService.class);
    
    public ArrayList<Agence> getAgences() throws IOException { 
        return Agences.getAgences();
    }
    
    public ArrayList<String> getCommunesNames() {
        return Agences.getCommunesNames();
    }
   
    public Agence getAgence(int idAgence) throws IOException {
           return Agence.getAgence(idAgence);
    }
    
    public ArrayList<Agence> getAgences(String communeName) throws IOException {
        return Agences.getAgences(Agences.getCommune(communeName));
    }
    
    public void downloadCsv(PrintWriter writer) throws IOException {
        writer.write("id, designation, realMaxWaitingTime, coordonneeX, coordonneeY, coordonneeXPrecise, coordonneeYPrecise, commmune \n");

        for (String communeName : Agences.getCommunesNames()) {
            Agences.Commune commune = Agences.getCommune(communeName);
            ArrayList<Agence> agences = Agences.getAgences(commune);
            for (Agence agence : agences) {
                writer.write(
                        agence.getIdAgence() + "," +
                                agence.getDesignation() + "," +
                                agence.getRealMaxWaitingTimeMs() + "," +
                                agence.getCoordonneeX() + "," +
                                agence.getCoordonneeY() + "," +
                                agence.getCoordonneeXPrecise() + "," +
                                agence.getCoordonneeYPrecise() + "," +
                                communeName + "\n");
            }
        }
        writer.close();
    }

}
