package com.ming.exception;


import sun.rmi.runtime.Log;

/**
 *  java 异常
 */
public class LoginException extends RuntimeException {
    public LoginException(){

    }

    public LoginException(String message){
        super(message);
    }
}
