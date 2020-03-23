/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriens.tempsattente.api.service;

import com.github.adriens.opt.tempsattente.sdk.Agence;
import com.github.adriens.opt.tempsattente.sdk.Agences;
import java.io.IOException;
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
    
}
