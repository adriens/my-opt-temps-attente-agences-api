/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriens.tempsattente.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author meilie
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TempsAttenteControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testRedirect() {
        System.out.println("redirect");
        
        String url = "http://localhost:8080/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAgences_0args() throws Exception {
        System.out.println("getAgencesAll");
        
        String url = "http://localhost:8080/temps-attente/agences";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        
        int i = 0;
        for (JsonNode jsonNode : root) {
            i++;
        }
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(65,i);
    }
     
    @Test
    public void testGetAgencesGood_String() throws Exception {
        System.out.println("getAgencesGoodCommuneName");
        
        String communeName = "noumea";
        String url = "http://localhost:8080/temps-attente/agences/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + communeName, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        
        int i = 0;
        for (JsonNode jsonNode : root) {
            i++;
        }
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(17, i);
    }
    
    @Test
    public void testGetAgencesBad_String() throws Exception {
        System.out.println("getAgencesBadCommuneName");
        
        String communeName = "noum";
        String url = "http://localhost:8080/temps-attente/agences/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + communeName, String.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetCommunesNames() throws Exception {
        System.out.println("getCommunesNames");
        
        String url = "http://localhost:8080/communes";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        
        int i = 0;
        for (JsonNode jsonNode : root) {
            i++;
        }
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(33, i);
    }

    @Test
    public void testGetAgenceGoodID() throws Exception {
        System.out.println("getAgenceGoodID");
        
        int idAgence = 4177;
        String url = "http://localhost:8080/temps-attente/agence/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + idAgence, String.class);
        
        ObjectMapper mapper = new ObjectMapper();
        String agence = "{\"idAgence\":4177,\"designation\":\"Agence philatélique CALEDOSCOPE\",\"realMaxWaitingTimeMs\":0,\"coordonneeX\":445205.7199999988,\"coordonneeY\":214495.6400000006,\"coordonneeXPrecise\":445227,\"coordonneeYPrecise\":214500}";
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(agence, response.getBody());
    }
    
    @Test
    public void testGetAgenceBadIDNotFound() throws Exception {
        System.out.println("getAgenceBadIDNotFound");
        
        int idAgence = 2;
        String url = "http://localhost:8080/temps-attente/agence/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + idAgence, String.class);
        
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    public void testGetAgenceBadIDBadRequest() throws Exception {
        System.out.println("getAgenceBadIDBadRequest");
        
        String idAgence = "toto";
        String url = "http://localhost:8080/temps-attente/agence/";
        ResponseEntity<String> response = restTemplate.getForEntity(url + idAgence, String.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
