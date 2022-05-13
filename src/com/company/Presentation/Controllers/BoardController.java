package com.company.Presentation.Controllers;

import com.company.Business.LogicModel;
import com.company.Presentation.MainController;
import com.company.Presentation.Views.BoardView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BoardController implements ActionListener {
    private LogicModel logicModel;
    private BoardView boardView;
    private MainController mainController;

    private int selectedCard;
    private boolean selectedType;

    public BoardController(LogicModel logicModel, BoardView boardView, MainController mainController) {
        this.boardView = boardView;
        this.mainController = mainController;
        this.logicModel = logicModel;
        this.selectedCard = -1;
        this.selectedType = false;

        boardView.configurePanel(this);
        boardView.configureCards(logicModel.setOffensiveCards(), logicModel.setDefensiveCards(),this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        if(e.getActionCommand().contains("OFF")) {
            int numCard = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(3)));
            System.out.println(logicModel.troopName(numCard-1, false));
            if (logicModel.selectTroop(numCard, false)) {
                //TODO marcar que hemos seleccionado la carta, quien haga la gameGUI
                selectedCard = numCard;
                selectedType = false;
            }
        }

        if(e.getActionCommand().contains("DEF")) {
            int numCard = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(3)));
            System.out.println(logicModel.troopName(numCard-1, true));
            if (logicModel.selectTroop(numCard, true)) {
                //TODO marcar que hemos seleccionado la carta, quien haga la gameGUI
                selectedCard = numCard;
                selectedType = true;
            }
        }

        System.out.println(selectedCard);

        if(selectedCard != -1) {
            if(e.getActionCommand().length() == 2) {
                logicModel.invokeTroop(selectedCard-1, selectedType, true, e.getActionCommand());
            }
        }
    }
}
