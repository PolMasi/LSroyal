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
            case MenuView.EXIT_BTN : mainController.switchView(MainView.START_VIEW);
            break;
            case MenuView.RANKING_BTN : rankingView.updateTable(userModel.getRanking()) ;
            mainController.switchView(MainView.RANKING_VIEW);
            break;

        }



    }
}
