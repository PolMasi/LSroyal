package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Presentation.MainController;
import com.company.Presentation.MainView;
import com.company.Presentation.Views.BoardView;
import com.company.Presentation.Views.MenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoardController implements ActionListener {
    private LogicModel logicModel;
    private BoardView boardView;
    private MainController mainController;
    private ScheduledExecutorService timer;
    private int selectedCard;
    private boolean selectedType;
    private boolean endGame;

    public static final String INSTRUCTIONS = "Damage the enemy towers to win the game. Avoid the enemies from damaging your towers or else you will lose. Best luck!";
    public static final String[] CONFIRM = {"Okey!"};
    public static final String[] SAVE_OPTIONS = {"Yes", "No"};
    public static final String WIN = "Congratulations! You have won!";
    public static final String LOSE = "Sorry! You have lose!";
    public static final String SAVE = " Do you want to save the game?";
    public static final String LEAVE = "Are you sure you want leave the game? This game will count as lose";

    public BoardController(LogicModel logicModel, BoardView boardView, MainController mainController) {
        this.boardView = boardView;
        this.mainController = mainController;
        this.logicModel = logicModel;
        this.selectedCard = -1;
        this.selectedType = false;

        mainController.setBoardController(this);
        boardView.configurePanel(this);
        boardView.configureCards(logicModel.setOffensiveCards(), logicModel.setDefensiveCards(),this);
    }


    private void updateTable() {
        String[][][] board = logicModel.updateBoard();
        JPanel[][] panels = boardView.getGrids();
        JButton[][] buttons = boardView.getGridButton();

        int userTroops = 0;
        int computerTroops = 0;

        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if(board[i][j] == null) {
                    panels[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setIcon(null);
                }
                else {
                    buttons[i][j].setIcon(new ImageIcon(BoardView.ICON_PATH+board[i][j][0]+BoardView.ICON_EXT));
                    if(board[i][j][2] == "true") {
                        panels[i][j].setBackground(Color.GREEN);        //nosotros carta
                        if (i != 7) {
                            userTroops++;
                        }
                    }
                    else {
                        panels[i][j].setBackground(Color.RED);          //carta enemigo
                        if (i != 0) {
                            computerTroops++;
                        }
                    }
                }
            }
        }

        boardView.updateTroopCounter(userTroops, computerTroops);
        updateLife();
    }

    private void updateLife() {
        int userHealth = logicModel.getUserHealth();
        int computerHealth = logicModel.getComputerHealth();

        boardView.updateLife(userHealth, computerHealth);

        if ((userHealth <= 0 || computerHealth <= 0) && !endGame) {
            boardView.setTimer(false);
            endGame = true;
            endGame(userHealth > 0);
        }
    }

    private void endGame(boolean win) {
        String text;

        timer.shutdown();

        if(win) {
            text = WIN;
        }
        else {
            text = LOSE;
        }

        text = text + SAVE;

        if (mainController.showConfirm(text, SAVE_OPTIONS) == 0) {
            System.out.println("Saving game");
        }

        mainController.switchView(MainView.MENU_VIEW);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals(MenuView.PLAYGAME_BTN)) {
            endGame = false;
            boardView.setTimer(true);
            logicModel.startGame();
            if (mainController.showConfirm(INSTRUCTIONS, CONFIRM) == 0) {
                timer = Executors.newScheduledThreadPool(1);
                timer.scheduleAtFixedRate(this.logicModel, 0, 1, TimeUnit.SECONDS);
            }
        }

        if(e.getActionCommand().equals(BoardView.BOARD_BACK)) {
            if (mainController.showConfirm(LEAVE, SAVE_OPTIONS) == 0) {
                timer.shutdown();
                boardView.setTimer(false);
                endGame = true;
                mainController.switchView(MainView.MENU_VIEW);
            }
        }

        if(e.getActionCommand().equals(BoardView.BOARD_TIMER)) {
            boardView.updateMoney(logicModel.getUserMoney());
            updateTable();
            updateLife();
        }

        if(e.getActionCommand().contains("OFF")) {
            int numCard = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(3)));
            //comprovar si tienes mana
            if (logicModel.canSelectTroop(numCard, false)) {
                selectedCard = numCard;
                selectedType = false;
            }
            else {
                System.out.println("No tienes suficiente dinero!");
                selectedCard = -1;      //no hay carta
            }
        }

        if(e.getActionCommand().contains("DEF")) {
            int numCard = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(3)));
            if (logicModel.canSelectTroop(numCard, true)) {
                selectedCard = numCard;
                selectedType = true;
            }
            else {
                System.out.println("No tienes suficiente dinero!");
                selectedCard = -1;
            }
        }

        if(selectedCard != -1) {
            if(e.getActionCommand().length() == 2) {
                if(logicModel.invokeTroop(selectedCard, selectedType, true, e.getActionCommand())) {
                    logicModel.spendMoney(selectedCard, selectedType, true);
                    selectedCard = -1;      //reinicia
                }
            }
        }
    }
}
