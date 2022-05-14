package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogicModel {
    private ComputerModel computerModel;
    private PlayerModel playerModel;
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;
    private ScheduledExecutorService timer;

    private int userHealth;
    private int userMoney;

    public LogicModel(ComputerModel computerModel, PlayerModel playerModel, ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive) {
        this.computerModel = computerModel;
        this.playerModel = playerModel;
        this.listOffensive = listOffensive;
        this.listDefensive = listDefensive;
        startGame();

        timer = Executors.newScheduledThreadPool(1);
        timer.scheduleAtFixedRate(this.computerModel, 0, 1, TimeUnit.SECONDS);
    }

    public String[] setDefensiveCards() {
        String[] defString = new String[listDefensive.size()*3];
        int j = 0;

        for (int i = 0; i < listDefensive.size(); i++) {
            defString[j] = listDefensive.get(i).getName();
            j++;
            defString[j] = String.valueOf(listDefensive.get(i).getCost());
            j++;
            defString[j] = "";      //ICON
            j++;
        }
        return defString;
    }

    public String[] setOffensiveCards() {
        String[] offString = new String[listOffensive.size()*3];
        int j = 0;

        for (int i = 0; i < listOffensive.size(); i++) {
            offString[j] = listOffensive.get(i).getName();
            j++;
            offString[j] = String.valueOf(listOffensive.get(i).getCost());
            j++;
            offString[j] = "";      //ICON
            j++;
        }
        return offString;
    }

    public String troopName(int numCard, boolean type) {
        if (type) {
            return listDefensive.get(numCard).getName();
        }
        return listOffensive.get(numCard).getName();
    }

    public void startGame() {
        userMoney = 5;
        userHealth = 100;
    }

    public boolean invokeTroop(int numCard, boolean type, String coords) {
        String cardName;

        if(type) {
            cardName = listDefensive.get(numCard-1).getName();
        }
        else {
            cardName = listOffensive.get(numCard-1).getName();
        }

        System.out.println("Player is invoking "+cardName+" in coordinates "+coords);

        if(coords.contains("5")) {
            System.out.println("Valid position!");
            return true;
        }
        else {
            System.out.println("Invalid position!");
            return false;
        }
    }

    public boolean canSelectTroop(int numCard, boolean type) {
        if(type) {
            return listDefensive.get(numCard-1).getCost() <= userMoney;
        }
        return listOffensive.get(numCard-1).getCost() <= userMoney;
    }

    public int getCost(int numCard, boolean type) {
        if(type) {
            return listDefensive.get(numCard-1).getCost();
        }
        return listOffensive.get(numCard-1).getCost();
    }

    private void addMoney(int money, boolean player) {

        if(player) {
            userMoney += money;
        }
        else {
            computerModel.addMoney(money);
        }
    }

    public void spendMoney(int numCard, boolean type, boolean player) {
        int cost;

        cost = getCost(numCard, type);
        addMoney(-cost, player);

    }

}

