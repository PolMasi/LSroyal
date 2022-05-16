package com.company.Presentation;

import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.Views.CardEnum;
import com.company.Presentation.Views.LoginView;
import com.company.Presentation.Views.SignupView;
import com.company.Presentation.Views.StartView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.setListeners(this);
    }

    /**
     * Funcion para cambiar la vista desde el controller
     * @param view
     */
    public void switchView(String view) {
        mainView.switchView(view);
    }

    public void showError(String error) {
        mainView.showError(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){

            /*case LoginView.BUTTON_LOGIN:
                if(loginController.login()){
                    System.out.println("OK");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Try again");
                }
                break;
            */
            case StartView.START_LOGIN_BTN:
                mainView.switchView(MainView.LOGIN_VIEW);
                break;

            case StartView.START_SIGNUP_BTN:
                mainView.switchView(MainView.SIGNUP_VIEW);
                break;

            case SignupView.SIGNUP_BACK_BTN:

            case LoginView.LOGIN_BACK_BTN:
                mainView.switchView(MainView.START_VIEW);
                break;

            case SignupView.SIGNUP_BTN:
                mainView.switchView(MainView.MENU_VIEW);
                break;



        }
    }
}
