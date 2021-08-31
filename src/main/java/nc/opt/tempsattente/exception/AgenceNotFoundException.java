/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nc.opt.tempsattente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author meilie
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgenceNotFoundException extends RuntimeException {
    
    public AgenceNotFoundException(String exception) {
    super(exception);
  }
}