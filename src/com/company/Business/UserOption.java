package com.company.Business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Control de les accions del login, signup, logout
 */
public class UserOption {

    public static final int MISMATCHING_PASS= 5;
    public  static final int USER_EXIST = 4;
    public  static final int INCORRECT_MAIL = 3;
    public  static final int INCORRECT_PASS = 6;
    public  static final int ERROR_SAVE = 1;
    public  static final int EVERYTHING_OK = 0;
    public  static final int EMPTY_FIELD = 2;
    public  static final int INCORRECT_LOGIN = 7;
    public  static final int DUPLICATED_LOGIN= 8;

    /**
     * control del mail
     * @param mail informació del mail
     * @return si está bé escrit
     */
    public boolean isEmail(String mail) {
        boolean result;

        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        Matcher matcher = pattern.matcher(mail);
        result = matcher.find();

        return result;
    }

    /**
     * control de la contrasenya
     * @param pass informació de la contrasenya
     * @return si está bé escrit
     */
    public boolean passwordValidator(String pass) {
        boolean result;

        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcher = pattern.matcher(pass);
        result = matcher.matches();

        return result;
    }

    /**
     * valida si el login es correcte
     * @param user nom del usuari
     * @param password contrasenya del usuari
     * @return si esta correcte o no
     */
    public int validLogin(String user, String password) {
        if (user.equals("") || password.equals("")) {
            return EMPTY_FIELD;
        }
        else {
            return EVERYTHING_OK;
        }
    }

    /**
     * valida si el sign up es correcte
     * @param user nom del ususari
     * @param password contrasenya del usuari
     * @param passwordConfirmation confirmacio contrasenya del usuari
     * @param email mail del usuari
     * @return si es correcte o no
     */
    public int validSignUp(String user, String password, String passwordConfirmation, String email) {

        if (user.equals("") || password.equals("") || email.equals("") || passwordConfirmation.equals("")) {
            return EMPTY_FIELD;
        }
        if (!password.equals(passwordConfirmation)) {
            return MISMATCHING_PASS;
        }
        if (!isEmail(email)) {
            return INCORRECT_MAIL;
        }
        if (!passwordValidator(password)) {
            return INCORRECT_PASS;

        }

        return EVERYTHING_OK;
    }

    /**
     * si introduceix corrrectament el nom que es vol borrar
     * @param userName nom
     * @param userNameDelete nom a esborrar
     * @return si se esborra correctament
     */
    public boolean correctUserNameDelete(String userName, String userNameDelete){

        return userName.equalsIgnoreCase(userNameDelete);
    }

}
