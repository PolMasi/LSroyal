package com.company.Presentation.Controllers;

import com.company.Business.BoardModel;
import com.company.Presentation.MainController;
import com.company.Presentation.Views.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {
    BoardModel boardModel;
    BoardView boardView;
    MainController mainController;

    public BoardController(BoardModel boardModel, BoardView boardView, MainController mainController) {
        this.boardModel = boardModel;
        this.boardView = boardView;
        this.mainController = mainController;
        boardView.configurePanel(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}