package com.company.Presentation.Controllers;

import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuView menuView;
    //private UserModel userModel;
    private MainController mainController;      //TODO preguntar Edu



    public MenuController(MenuView menuView, MainController mainController) {
        this.menuView = menuView;
        //this.userModel = userModel;
        this.mainController = mainController;
        registerMenuViewListener();
    }

    public void registerMenuViewListener() {
        this.menuView.registerMenuController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case MenuView.PLAYGAME_BTN -> mainController.switchView(MainView.BOARD_VIEW);
            case MenuView.EXIT_BTN -> mainController.switchView(MainView.START_VIEW);
        }



    }
}
