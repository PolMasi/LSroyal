package com.company.Business;

import com.company.Persistence.UserDAO;

import java.util.ArrayList;

public class UserModel {

    private UserDAO userDAO;
    private UserOption userOption;

    public String userName;



    public UserModel(UserDAO userDAO, UserOption userOption) {
        this.userDAO = userDAO;
        this.userOption= userOption;
    }

    /**
     * LOGIN
     * @param userName
     * @param password
     * @return
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
     * SIGNUP
     * @param password
     * @param passwordConfirmation
     * @param email
     * @param user
     * @return
     */


    public int signUp(String user, String password, String passwordConfirmation, String email) {

        switch(userOption.validSignUp(user, password, passwordConfirmation, email)) {
            case UserOption.EVERYTHING_OK:
                System.out.println("Holaaaaaaaa");

                 if(!userDAO.checkUserName(user)){
                     System.out.println("Adioooos");
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
     * LOGOUT
     */
    public void logout() {
        userName = null;
    }

    public boolean delete(String userName){

        if (!userOption.correctUserNameDelete(this.userName, userName)){

            return false;
        }

        userDAO.delete(userName);
        return true;
    }

    public void setUser(String userName) {
        this.userName = userName;
    }


    //convertimos de array list a matriz
    public String[][] getRanking () {

        ArrayList<String[]> list =userDAO.getRanking();

        String[][] matrix = new String[list.size()][3];

        for (int i = 0; i < list.size(); i++) {
            matrix[i] = list.get(i);
        }
        return matrix;
    }


}
