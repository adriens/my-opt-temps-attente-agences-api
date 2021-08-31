/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.opt.tempsattente.api.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import nc.opt.tempsattente.Agence;
import nc.opt.tempsattente.api.service.TempsAttenteService;
import nc.opt.tempsattente.exception.AgenceNotFoundException;
import nc.opt.tempsattente.exception.CommuneNotFoundException;

/**
 *
 * @author meilie
 */
@RestController
public class TempsAttenteController {

    @Autowired
    private TempsAttenteService tempsAttenteService;

    private final Logger log = LoggerFactory.getLogger(TempsAttenteController.class);
    
    @GetMapping("/")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public RedirectView redirect() throws Exception {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/doc.tempsattente.html");
        return redirectView;
    }
    
    @GetMapping("/temps-attente/agences")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Agence> getAgences() throws IOException {
        try {
            return tempsAttenteService.getAgences();
        } catch (Exception ex) {
            log.error("Impossible de récupérer les détails des agences");
            throw ex;
        }
    }

    @GetMapping("/temps-attente/agences/{communeName}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<Agence> getAgences(@PathVariable String communeName) throws IOException {
        if(tempsAttenteService.getAgences(communeName).isEmpty()) {
            throw new CommuneNotFoundException("la commune <" +communeName+ "> n'existe pas.\n");
        } else { 
            return tempsAttenteService.getAgences(communeName);
        }
    }

    @GetMapping("/communes")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<String> getCommunesNames() {
        try {
            return tempsAttenteService.getCommunesNames();
        } catch (Exception ex) {
            log.error("Impossible de récupérer les détails des communes");
            throw ex;
        }
    }

    @GetMapping("/temps-attente/agence/{idAgence}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Agence getAgence(@PathVariable int idAgence) throws IOException {
        if(tempsAttenteService.getAgence(idAgence) == null) {
            throw new AgenceNotFoundException("l'ID d'agence <" +idAgence+ "> n'existe pas.\n");
        } else { 
            return tempsAttenteService.getAgence(idAgence);
        }
    }

    @GetMapping("/csv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
        String timestamp = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
        response.setContentType("text/csv;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=tempsattente-" + timestamp + ".csv");
        tempsAttenteService.downloadCsv(response.getWriter());
    }

}
