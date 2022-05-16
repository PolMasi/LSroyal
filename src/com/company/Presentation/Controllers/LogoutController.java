package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.LogoutView;
import com.company.Presentation.Views.StartView;

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

        switch (e.getActionCommand()){

            case StartView.START_DELETE_BTN:

                String user = mainController.showInput(MainView.DELETE_MSG);

                if(userModel.delete(user)){

                    mainController.switchView(MainView.START_VIEW);

                }else{

                    mainController.showError("The username is not correct");
                }
            break;

            case LogoutView.LOGOUT_BTN:

                userModel.logout();
                mainController.switchView(MainView.START_VIEW);

        }

    }
}
