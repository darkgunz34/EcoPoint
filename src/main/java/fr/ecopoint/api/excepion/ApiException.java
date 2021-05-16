package fr.ecopoint.api.excepion;

public class ApiException extends Exception{
    public ApiException(final String message){
        super(message);
    }
}
