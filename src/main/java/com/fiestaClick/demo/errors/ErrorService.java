package com.fiestaClick.demo.errors;

/**
 *
 * @author 54261
 */
public class ErrorService extends Exception {
    public ErrorService(String msn) {
        super(msn);
    }
}
