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

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().contains("OFF")) {
            int numCard = Integer.parseInt(String.valueOf(e.getActionCommand().charAt(3)));

            //comprovar si tienes mana
            if (logicModel.canSelectTroop(numCard, false)) {
                //TODO marcar que hemos seleccionado la carta, quien haga la gameGUI
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
                //TODO marcar que hemos seleccionado la carta, quien haga la gameGUI
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
