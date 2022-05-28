package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Business.UserModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.GameListView;
import com.company.Presentation.Views.MenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Gestiona el comportament de la vista de la lista del joc
 */
public class GameListController implements ActionListener {

     private LogicModel logicModel;
     private GameListView gameListView;
     private MainController mainController;


    /**
     *
     * @param logicModel conté la informació de com funcionen les cartes
     * @param gameListView conte informació de la vista del la lista del joc
     * @param mainController conte informació el controlador principal de les vistes
     */
    public GameListController(LogicModel logicModel, GameListView gameListView, MainController mainController){

        this.logicModel = logicModel;
        this.gameListView = gameListView;
        this.mainController = mainController;

        mainController.setGameListController(this);


        }

    /**
     * mostra mistges depenent de la acció y el event actual al sign up
     * @param e variable per controlar la acció
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(MenuView.REPRODUCE_BTN)){

            updateList();
        }

        if(e.getActionCommand().equals(GameListView.GAMELIST_BACK)){

            mainController.switchView(MainView.MENU_VIEW);
        }
    }


    /**
     * Actualiza la lista
     */
    public void updateList(){

        gameListView.configurePanel(logicModel.getGames(), this);


    }

}