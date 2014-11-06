/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.polytech.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Epulapp
 */
public class ExceptionHandler {
    
    public static ResponseEntity<String> handle(Exception e) {
        
        if (e instanceof java.net.ConnectException) {
            return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    public ExceptionHandler() {
        super();
    }
    
}
