package com.company.Presentation.Controllers;

import com.company.Business.UserModel;
import com.company.Main;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.MenuView;
import com.company.Presentation.Views.RankingView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController implements ActionListener {
    private MenuView menuView;
    private UserModel userModel;
    private MainController mainController;      //TODO preguntar Edu
    private RankingView rankingView;
    private final static String LOGOUT_TEXT = "Do you want to logout?";
    private final static String DELETE_TEXT = "If you want to delete your account write your username:";
    private final static String[] LOGOUT_OPT = {"YES","NO"};


    public MenuController(MenuView menuView, UserModel  userModel, MainController mainController, RankingView rankingView) {
        this.menuView = menuView;
        this.userModel = userModel;
        this.mainController = mainController;
        this.rankingView = rankingView;
        registerMenuViewListener();
    }

    public void registerMenuViewListener() {
        this.menuView.registerMenuController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case MenuView.PLAYGAME_BTN : mainController.switchView(MainView.BOARD_VIEW);
            break;
            case MenuView.LOGOUT_BTN :
                if(mainController.showConfirm(LOGOUT_TEXT, LOGOUT_OPT) == 0) {
                    userModel.logout();
                    mainController.switchView(MainView.START_VIEW);
                }
            break;
            case MenuView.DELETE_BTN:
                System.out.println(userModel.userName);
                if(mainController.showInput(DELETE_TEXT).equalsIgnoreCase(userModel.userName)) {
                    userModel.delete(userModel.userName);
                    mainController.switchView(MainView.START_VIEW);
                }
                break;
            case MenuView.RANKING_BTN : rankingView.updateTable(userModel.getRanking()) ;
            mainController.switchView(MainView.RANKING_VIEW);
            break;

        }



    }
}
