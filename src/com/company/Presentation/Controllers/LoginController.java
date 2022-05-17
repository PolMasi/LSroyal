package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Business.UserOption;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestiona el comportamiento del view del login
 */
public class LoginController implements ActionListener {
    private UserModel userModel;
    private LoginView loginView;
    private MainController mainController;      //TODO

    public LoginController(UserModel userModel, LoginView loginView, MainController mainController) {
        this.userModel = userModel;
        this.loginView = loginView;
        this.mainController = mainController;
        registerLoginListener();
    }

    /**
     * Detecta cuando se pulsa el boton del login
     */
    public void registerLoginListener() {
        this.loginView.registerLoginController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(userModel.login(loginView.getUser(), loginView.getPassword())) {
            case UserOption.EVERYTHING_OK ->  mainController.switchView(MainView.MENU_VIEW);
            case UserOption.EMPTY_FIELD -> mainController.showError("There is an empty field!");
            case UserOption.INCORRECT_LOGIN -> mainController.showError("Username or password incorrect!");
        }

        if(userModel.login(loginView.getUser(), loginView.getPassword()) == UserOption.EVERYTHING_OK) {
            mainController.switchView(MainView.START_VIEW);
        }
    }

}
