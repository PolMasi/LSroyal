package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Presentation.MainController;
import com.company.Presentation.Views.BoardView;

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

    public BoardController(LogicModel logicModel, BoardView boardView, MainController mainController) {
        this.boardView = boardView;
        this.mainController = mainController;
        this.logicModel = logicModel;
        this.selectedCard = -1;
        this.selectedType = false;

        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(this.logicModel, 0, 1, TimeUnit.SECONDS);
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
                        userTroops++;
                    }
                    else {
                        panels[i][j].setBackground(Color.RED);          //carta enemigo
                        computerTroops++;
                    }
                }
            }
        }

        boardView.updateTroopCounter(userTroops, computerTroops);
    }

    private void updateLife() {
        boardView.updateLife(logicModel.getUserHealth(), logicModel.getComputerHealth());
    }


    @Override
    public void actionPerformed(ActionEvent e) {

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
