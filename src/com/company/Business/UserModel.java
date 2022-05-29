package com.company.Business;

import com.company.Persistence.UserDAO;

import java.util.ArrayList;

/**
 * control de la informació d'un usuari per jugar la partida
 */
public class UserModel {

    private UserDAO userDAO;
    private UserOption userOption;

    public String userName;


    /**
     * contructor del userModel
     * @param userDAO control del DAO del usuari
     * @param userOption control de la opcion del usuari
     */
    public UserModel(UserDAO userDAO, UserOption userOption) {
        this.userDAO = userDAO;
        this.userOption= userOption;
    }

    /**
     * LOGIN del usuario
     * @param userName nombre del usuario
     * @param password la contraseña
     * @return si ha logeado correctamente
     */
    public int login(String userName, String password) {

        switch (userOption.validLogin(userName, password)) {

            case UserOption.EMPTY_FIELD:
                return UserOption.EMPTY_FIELD;

            case UserOption.EVERYTHING_OK:
                if(userDAO.validLogin(userName, password)) {
                    this.userName = userDAO.getUserName(userName);
                    return UserOption.EVERYTHING_OK;
                }
                else {
                    return UserOption.INCORRECT_LOGIN;
                }

            default: return UserOption.INCORRECT_LOGIN;
        }
    }

    /**
     * SIGNUP del usuari
     * @param password la contrasenya
     * @param passwordConfirmation la confiramcio de la contrasenya
     * @param email el mail del usuari
     * @param user el ususari registrat
     * @return si ha fet el sign up correctament
     */


    public int signUp(String user, String password, String passwordConfirmation, String email) {

        switch(userOption.validSignUp(user, password, passwordConfirmation, email)) {
            case UserOption.EVERYTHING_OK:

                 if(!userDAO.checkUserName(user)){

                    return UserOption.DUPLICATED_LOGIN;
                }

                if(userDAO.validSignUp(user, password, email)) {

                    setUser(userName);      //que usuario ha iniciado sesion
                    return UserOption.EVERYTHING_OK;
                }
                else {
                    return UserOption.ERROR_SAVE;
                }

            case UserOption.EMPTY_FIELD:
                return UserOption.EMPTY_FIELD;

            case UserOption.INCORRECT_MAIL:
                return UserOption.INCORRECT_MAIL;

            case UserOption.INCORRECT_PASS:
                return UserOption.INCORRECT_PASS;

            case UserOption.MISMATCHING_PASS:
               return UserOption.MISMATCHING_PASS;

            default: return UserOption.ERROR_SAVE;
        }
    }

    /**
     * LOGOUT del usuari
     */
    public void logout() {
        userDAO.logOutUserID();
        userName = null;

    }

    /**
     * esborrar el ususari
     * @param userName el nom d'ususari
     * @return si ho realitza  o no
     */
    public boolean delete(String userName){

        if (!userOption.correctUserNameDelete(this.userName, userName)){

            return false;
        }

        userDAO.delete(userName);
        return true;
    }

    /**
     * setter del nom del usuari
     * @param userName el nom registrat
     */
    public void setUser(String userName) {
        this.userName = userName;
    }


    /**
     * convertimos de array list a matriz para el ranking de las partidas
     * @return la matriu creada
     */
    public String[][] getRanking () {

        ArrayList<String[]> list =userDAO.getRanking();

        String[][] matrix = new String[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            matrix[i] = list.get(i);
        }
        return matrix;
    }


}
