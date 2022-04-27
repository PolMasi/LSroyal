package com.company.Presentation;

import com.company.Presentation.Views.LoginView;

import java.awt.event.ActionListener;

public class MainView {

    private LoginView loginView;

    public MainView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void registerListener(ActionListener listener){
        loginView.registerController(listener);
    }
}
