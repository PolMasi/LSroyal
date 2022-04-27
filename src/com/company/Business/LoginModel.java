package com.company.Business;

import com.company.Persistence.UserDAO;

public class LoginModel {
    private UserDAO userDAO;
    private String userName;

    public LoginModel(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean login(String userName, String password) {
        if(userDAO.validLogin(userName, password)) {
            this.userName = userName;
            return true;
        }
        return false;
    }
}
