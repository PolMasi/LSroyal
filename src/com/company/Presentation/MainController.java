package com.company.Presentation;

import com.company.Presentation.Controllers.LoginController;
import com.company.Presentation.Views.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController implements ActionListener {
    private LoginController loginController;
    private MainView mainView;

    public MainController(LoginController loginController, MainView mainView) {
        this.loginController = loginController;
        this.mainView = mainView;


        mainView.registerListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()){
            case LoginView.BUTTON_LOGIN:
                if(loginController.login()){
                    System.out.println("OK");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Try again");
                }
                break;

        }
    }
}
