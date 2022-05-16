package com.company.Business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


    public boolean isEmail(String mail) {
        boolean result;

        Pattern pattern = Pattern.compile("^(.+)@(\\S+)$");
        //Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[.A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(mail);
        result = matcher.find();

        return result;
    }


    public boolean PasswordValidator (String pass) {
        boolean result;

        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        Matcher matcher = pattern.matcher(pass);
        result = matcher.matches();

        return result;
    }

    public int validLogin(String user, String password) {
        if (user.equals("") || password.equals("")) {
            return EMPTY_FIELD;
        }
        else {
            return EVERYTHING_OK;
        }
    }

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
        if (!PasswordValidator(password)) {
            //return INCORRECT_PASS;
            return EVERYTHING_OK;
        }

        return EVERYTHING_OK;
    }

    public boolean correctUserNameDelete(String userName, String userNameDelete){

        return userName.equalsIgnoreCase(userNameDelete);
    }

}
