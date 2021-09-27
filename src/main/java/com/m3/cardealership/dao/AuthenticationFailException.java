/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.m3.cardealership.dao;

/**
 *
 * @author junha
 */
public class AuthenticationFailException extends Exception {

        public AuthenticationFailException(String message) {
        super(message);
    }

    public AuthenticationFailException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
