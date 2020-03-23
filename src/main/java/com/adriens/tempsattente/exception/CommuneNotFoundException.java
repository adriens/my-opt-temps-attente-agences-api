/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.adriens.tempsattente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author meilie
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommuneNotFoundException extends RuntimeException {
    
    public CommuneNotFoundException(String exception) {
    super(exception);
  }
}
