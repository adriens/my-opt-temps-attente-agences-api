/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.opt.tempsattente.api.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import nc.opt.tempsattente.Agence;
import nc.opt.tempsattente.Agences;

/**
 *
 * @author meilie
 */

@Service
public class TempsAttenteService {
    
    private final Logger log = LoggerFactory.getLogger(TempsAttenteService.class);
    
    public List<Agence> getAgences() throws IOException { 
        return Agences.getAgences();
    }
    
    public List<String> getCommunesNames() {
        return Agences.getCommunesNames();
    }
   
    public Agence getAgence(int idAgence) throws IOException {
           return Agence.getAgence(idAgence);
    }
    
    public List<Agence> getAgences(String communeName) throws IOException {
        return Agences.getAgences(Agences.getCommune(communeName));
    }
    
    public void downloadCsv(PrintWriter writer) throws IOException {
        writer.write("id, designation, realMaxWaitingTime, coordonneeX, coordonneeY, coordonneeXPrecise, coordonneeYPrecise, commmune \n");

        for (String communeName : Agences.getCommunesNames()) {
            Agences.Commune commune = Agences.getCommune(communeName);
            List<Agence> agences = Agences.getAgences(commune);
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
