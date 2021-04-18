package fr.ecopoint.model.constante;

public class UserConstante {

    private UserConstante(){

    }

    public static final String REGEX_VALIDATION_MAL = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static final String REGEX_VALIDATION_TEL = "^\\d{10}$";

}
