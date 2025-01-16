
package org.example.onlinecourse.exceptions;


public class DataAlreadyExists extends RuntimeException{
    public DataAlreadyExists(String message){
        super(message);
    }
}
