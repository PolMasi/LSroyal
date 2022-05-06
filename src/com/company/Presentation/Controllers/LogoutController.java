package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.LogoutView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutController implements ActionListener {
    private LogoutView logoutView;
    private UserModel userModel;
    private MainController mainController;      //TODO

    public LogoutController(UserModel userModel, LogoutView logoutView, MainController mainController) {
        this.logoutView = logoutView;
        this.userModel = userModel;
        this.mainController = mainController;
        registerLogoutListener();
    }

    public void registerLogoutListener() {this.logoutView.registerLogoutController(this);}

    @Override
    public void actionPerformed(ActionEvent e) {
        userModel.logout();
        mainController.switchView(MainView.START_VIEW);
    }
}
