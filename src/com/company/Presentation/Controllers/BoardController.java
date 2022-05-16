package com.company.Presentation.Controllers;

import com.company.Business.ComputerModel;
import com.company.Business.LogicModel;
import com.company.Business.PlayerModel;
import com.company.Presentation.MainController;
import com.company.Presentation.Views.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoardController implements ActionListener {
    ComputerModel computerModel;
    BoardView boardView;
    MainController mainController;
    ScheduledExecutorService timer;

    public BoardController(ComputerModel computerModel, BoardView boardView, MainController mainController) {

        this.boardView = boardView;
        this.mainController = mainController;
        this.computerModel = computerModel;
        boardView.configurePanel(this);

        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(computerModel, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
    }
}
