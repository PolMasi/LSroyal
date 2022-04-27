package com.company.Presentation.Controllers;

import com.company.Business.LoginModel;
import com.company.Presentation.Views.LoginView;

import java.awt.event.ActionListener;

/**
 * Gestiona el comportamiento del view del login
 */
public class LoginController {
    private LoginModel loginModel;
    private LoginView loginView;

    public LoginController(LoginModel loginModel, LoginView loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }
    public boolean login(){
       return loginModel.login(loginView.getUser(), loginView.getPassword());
    }



}
