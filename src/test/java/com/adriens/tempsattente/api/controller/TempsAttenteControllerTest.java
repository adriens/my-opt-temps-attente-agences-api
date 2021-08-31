/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriens.tempsattente.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import com.adriens.tempsattente.api.service.TempsAttenteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import nc.opt.tempsattente.Agence;

/**
 *
 * @author meilie
 */

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TempsAttenteController.class, TempsAttenteService.class})
@WebMvcTest
public class TempsAttenteControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper mapper;
    
    @Test
    public void testRedirect() throws Exception {
        System.out.println("redirect");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isFound())
        .andReturn();
        
        assertEquals("/doc.tempsattente.html", result.getResponse().getRedirectedUrl());
    }

    @Test
    public void testGetAgences_0args() throws Exception {
        System.out.println("getAgencesAll");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agences")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
        
        ArrayList<Agence> listeAgence = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ArrayList<Agence>>(){});
        
        assertEquals(60, listeAgence.size());
    }
    
    @Test
    public void testGetAgencesGood_String() throws Exception {
        System.out.println("getAgencesGoodCommuneName");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agences/{communeName}","moindou")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
        
        ArrayList<Agence> listeAgence = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ArrayList<Agence>>(){});
        
        assertEquals(1,listeAgence.size());
    }
    
    @Test
    public void testGetAgencesBad_String() throws Exception {
        System.out.println("getAgencesBadCommuneName");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agences/{communeName}","toto")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andReturn();
        
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }

    @Test
    public void testGetCommunesNames() throws Exception {
        System.out.println("getCommunesNames");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/communes")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
        
        ArrayList<String> listeCommunes = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<ArrayList<String>>(){});
        
        assertEquals(34, listeCommunes.size());
    }

    @Test
    public void testGetAgenceGoodID() throws Exception {
        System.out.println("getAgenceGoodID");
       
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agence/{idAgence}",4306)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
        
        Agence agence = mapper.readValue(result.getResponse().getContentAsString(), Agence.class);
        String strAgence = "Agence de LA FOA";
        
        assertEquals(strAgence, agence.getDesignation());
    }
    
    @Test
    public void testGetAgenceBadIDNotFound() throws Exception {
        System.out.println("getAgenceBadIDNotFound");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agence/{idAgence}",2)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound())
        .andReturn();
        
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
    
    @Test
    public void testGetAgenceBadIDBadRequest() throws Exception {
        System.out.println("getAgenceBadIDBadRequest");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/temps-attente/agence/{idAgence}","toto")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
        
        assertTrue(result.getResponse().getContentAsString().isEmpty());
    }
    
    @Test
    public void testGetCsv() throws Exception {
        System.out.println("getCsv");
        
        MvcResult result = (MvcResult) mvc.perform( MockMvcRequestBuilders
        .get("/csv")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();
        
        assertTrue(result.getResponse().getContentType().equalsIgnoreCase("text/csv;charset=UTF-8"));
    }
}
