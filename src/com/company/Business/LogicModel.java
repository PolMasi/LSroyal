package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogicModel implements Runnable {
    private ComputerModel computerModel;
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;


    private int counter;
    private int computerHealth;
    private int computerMoney;
    private Random selectTroop;

    private int userHealth;
    private int userMoney;

    public LogicModel(ComputerModel computerModel, ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive) {
        this.computerModel = computerModel;
        this.listOffensive = listOffensive;
        this.listDefensive = listDefensive;
        this.selectTroop = new Random();
        startGame();


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
        computerMoney = 1;
        computerHealth = 100;
        userMoney = 5;
        userHealth = 100;
    }

    public boolean invokeTroop(int numCard, boolean type, boolean player,String coords) {
        String cardName;

        if(type) {
            cardName = listDefensive.get(numCard-1).getName();
        }
        else {
            cardName = listOffensive.get(numCard-1).getName();
        }
        if(player){
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
        System.out.println("Computer is invoking "+cardName+" in coordinates"+coords);
        return true;
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

    @Override
    public void run() {
        defenseTroop();

        if(counter == 4){
            attackTroop();
        }
    }
    public void defenseTroop(){
        //System.out.println("defensa");
        int troop = selectTroop.nextInt(listDefensive.size());
        boolean invoked = false;
        Random coords = new Random();
        String coordinates;

        Defensive defTroop = listDefensive.get(troop);
        //System.out.println("1." + defTroop.getCost());
        if(defTroop.getCost() > computerMoney){
            Collections.shuffle(listDefensive);
            for (int i = 0; i < listDefensive.size(); i++) {

                if(listDefensive.get(i).getCost() <= computerMoney && !invoked){
                   do{
                       coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                       invoked = invokeTroop(i+1, true, false, coordinates);

                   }while (!invoked);

                    //System.out.println("2. new "+ d.getCost());
                }
            }
        }
        else{
            do{
                coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                invoked = invokeTroop(troop+1, true, false, coordinates);
            }while (!invoked);

        }
        counter++;

    }
    public void attackTroop(){
        //System.out.println("ataque");
        int troop = selectTroop.nextInt(listOffensive.size());
        boolean invoked = false;
        Random coords = new Random();
        String coordinates;

        Offensive offensive = listOffensive.get(troop);
        //System.out.println("1." + defTroop.getCost());
        if(offensive.getCost() > computerMoney){
            Collections.shuffle(listDefensive);
            for (int i = 0; i < listOffensive.size(); i++) {

                if(listOffensive.get(i).getCost() <= computerMoney && !invoked){
                    do{
                        coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                        invoked = invokeTroop(i+1, false, false, coordinates);

                    }while (!invoked);

                    //System.out.println("2. new "+ d.getCost());
                }
            }
        }
        else{
            do{
                coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                invoked = invokeTroop(troop+1, false, false, coordinates);
            }while (!invoked);

        }
        counter = 0;

    }
    private String getCoordinate(int x, int y){
        String coords;
        x = x+1;
        y = y +1;

        switch (y){
            case 1-> coords = x+"a";
            case 2-> coords = x+"b";
            case 3-> coords = x+"c";
            case 4-> coords = x+"d";
            case 5-> coords = x+"e";
            case 6-> coords = x+"f";
            case 7-> coords = x+"g";
            default -> coords = "error";

        }
        return coords;
    }

}

