package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Business.UserModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.GameListView;
import com.company.Presentation.Views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameListController implements ActionListener {

     private LogicModel logicModel;
     private GameListView gameListView;
     private MainController mainController;





    public GameListController(LogicModel logicModel, GameListView gameListView, MainController mainController){

        this.logicModel = logicModel;
        this.gameListView = gameListView;
        this.mainController = mainController;

        mainController.setGameListController(this);


        }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(MenuView.REPRODUCE_BTN)){

            updateList();
        }

        if(e.getActionCommand().equals(GameListView.GAMELIST_BACK)){

            mainController.switchView(MainView.MENU_VIEW);
        }
    }


    public void registerController(){



    }

    public void updateList(){

        gameListView.configurePanel(logicModel.getGames(), this);


    }

}