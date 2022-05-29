package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.BoardView;
import com.company.Presentation.Views.GameListView;
import com.company.Presentation.Views.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Gestiona el comportament de la vista de la lista del joc
 */
public class ReplayController implements ActionListener {

     private LogicModel logicModel;
     private GameListView gameListView;
     private MainController mainController;
     private BoardView boardView;
     private Timer timer;
     private Boolean replayingGame;
     private String[][][] board;
     private final static  String REPLAY_TIMER = "REPLAY_TIMER";
     private final static  String REPLAY_END = "The Replay has ended!";
     private int userHealth;
     private int computerHealth;

    /**
     *
     * @param logicModel conté la informació de com funcionen les cartes
     * @param gameListView conte informació de la vista del la lista del joc
     * @param mainController conte informació el controlador principal de les vistes
     */
    public ReplayController(LogicModel logicModel, GameListView gameListView, MainController mainController, BoardView boardView){

        this.logicModel = logicModel;
        this.gameListView = gameListView;
        this.mainController = mainController;
        this.boardView = boardView;
        this.replayingGame = false;
        timer = new Timer(500,this);
        timer.setActionCommand(REPLAY_TIMER);


        mainController.setGameListController(this);


        }

    /**
     * mostra mistges depenent de la acció y el event actual al sign up
     * @param e variable per controlar la acció
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals(MenuView.REPRODUCE_BTN)){

            this.replayingGame = false;
            timer.start();
            updateList();
        }

        if(e.getActionCommand().equals(GameListView.GAMELIST_BACK)){

            mainController.switchView(MainView.MENU_VIEW);
        }

        if(e.getActionCommand().equals(ReplayController.REPLAY_TIMER)){

            if(gameListView.getSelectedGame()){

                logicModel.getReplayGame(gameListView.getGameID());
                mainController.switchView(MainView.BOARD_VIEW);
                gameListView.setSelectedGame(false);
                this.replayingGame = true;
                computerHealth = 4000;
                userHealth = 4000;

            }

            if(replayingGame){

                String [][][] move = logicModel.getReplayMove();

                if(board != null){

                    if(board != (move)){

                        updateTable(move);
                        board = move;
                    }

                }else{

                    updateTable(move);
                    board = move;

                }


            }

        }
    }


    /**
     * Actualiza la lista
     */
    public void updateList(){

        gameListView.configurePanel(logicModel.getGames(), this);


    }

    private void updateTable(String[][][] board) {

        if(board == null){

            endGame();
            return;


        }

        JPanel[][] panels = boardView.getGrids();
        JButton[][] buttons = boardView.getGridButton();


        int userTroops = 0;
        int computerTroops = 0;

        for (int i = 0; i < BoardView.ROWS; i++) {

            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if(board[i][j] == null) {
                    panels[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setIcon(null);
                }
                else {
                    buttons[i][j].setIcon(new ImageIcon(BoardView.ICON_PATH+board[i][j][0]+BoardView.ICON_EXT));
                    System.out.println(board[i][j][2]);
                    if(board[i][j][2].equals("true")) {
                        buttons[i][j].setBackground(Color.GREEN);
                        panels[i][j].setBackground(Color.GREEN);        //nosotros carta
                        if (i != 7) {
                            userTroops++;
                        }
                    }
                    if(board[i][j][2].equals("false")) {

                        buttons[i][j].setBackground(Color.RED);
                        panels[i][j].setBackground(Color.RED);          //carta enemigo
                        if (i != 0) {
                            computerTroops++;
                        }
                    }
                }
                if(board[i][j] != null){
                    System.out.println(board[i][j][0]);

                    if (board[i][j][0].equals("torres")) {
                        System.out.println(Integer.getInteger(board[i][j][3]));

                        if (board[i][j][2].equals("true")) {
                            userHealth -= 1000 - Integer.parseInt(board[i][j][3]);
                        }
                        else {
                            computerHealth -= 1000 - Integer.parseInt(board[i][j][3]);
                        }
                    }


                }


            }
        }

        boardView.updateTroopCounter(userTroops, computerTroops);
        boardView.updateLife(userHealth, computerHealth);
        //updateLife();
    }



    private void endGame() {

        timer.stop();

        mainController.showConfirm(REPLAY_END, new String[]{"OK"});

        mainController.switchView(MainView.MENU_VIEW);

    }


}