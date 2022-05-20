package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Business.Entities.Troop;
import com.company.Presentation.Views.BoardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LogicModel implements Runnable {
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;

    private int counter;
    private int computerHealth;
    private int computerMoney;
    private Random selectTroop;

    private int userHealth;
    private int userMoney;

    //matriz de posicion de tropas
    private Troop[][] matrixTroops;

    public int getUserMoney() {
        return userMoney;
    }

    public LogicModel(ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive) {
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
        userHealth = 99;
        matrixTroops = new Troop[BoardView.ROWS][BoardView.COLUMNS];
    }

    public boolean invokeTroop(int numCard, boolean type, boolean player,String coords) {

        Troop troop;
        int[] coordinates = getIntCoordinate(coords);

        if(type) {
            troop = listDefensive.get(numCard-1);
        }
        else {
            troop = listOffensive.get(numCard-1);
        }

        troop.setLastCoordinate(new int[]{-1, -1});

        if(matrixTroops[coordinates[0]][coordinates[1]] != null) {
            return false;
        }

        if (player) {
            System.out.println("Player is invoking " + troop.getName() + " in coordinates " + coords);

            //TODO comprovar que el 5 este validando bien donde se puede poner la tropa
            if (coords.contains("5") || coords.contains("6") || coords.contains("7") || coords.contains("8")) {
                System.out.println("Valid position!");

                //pasar corrdenadas a numeros para la matriz

                //llenar matriz tropas para saber donde se guarda
                troop.setPlayer(player);
                System.out.println(coordinates);
                editMatrix(coordinates, troop);

                return true;
            } else {
                System.out.println("Invalid position!");
                return false;
            }
        }

        //guardar informacion computer
        troop.setPlayer(player);
        editMatrix(coordinates, troop);

        System.out.println("Computer is invoking "+troop.getName()+" in coordinates"+coords);
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
            computerMoney += money;
        }
    }

    public void spendMoney(int numCard, boolean type, boolean player) {
        int cost;

        cost = getCost(numCard, type);
        addMoney(-cost, player);

    }

    @Override
    public void run() {

        System.out.println("run");
        defenseTroop();
        if(counter == 3) {
            moveTroops();
        }

        if(counter == 4){
            invokeComputerAtack();
            passiveMoney();
        }
    }

    public void moveTroops() {

        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if (matrixTroops[i][j] != null) {
                    editMatrix(matrixTroops[i][j].move(matrixTroops), matrixTroops[i][j]);
                }
            }
        }
    }


    public void defenseTroop() {
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
                    addMoney(-defTroop.getCost(), false);

                    //System.out.println("2. new "+ d.getCost());
                }
            }
        }
        else{
            do{
                coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                invoked = invokeTroop(troop+1, true, false, coordinates);
            }while (!invoked);
            addMoney(-defTroop.getCost(), false);


        }
        counter++;

    }
    public void invokeComputerAtack(){
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
                    addMoney(-offensive.getCost(), false);

                                        //System.out.println("2. new "+ d.getCost());
                }
            }
        }
        else{
            do{
                coordinates = getCoordinate(coords.nextInt(4), coords.nextInt(7) );
                invoked = invokeTroop(troop+1, false, false, coordinates);
            }while (!invoked);
            addMoney(-offensive.getCost(), false);

        }
        counter = 0;

    }
    private String getCoordinate(int x, int y){
        String coords;
        x = x +1;
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

    public int getComputerHealth() {
        return computerHealth;
    }

    public int getUserHealth() {
        return userHealth;
    }

    private int[] getIntCoordinate(String coordinate) {

        int[] coordinateInt;
        coordinateInt = new int[2];
        coordinateInt[0] = Integer.parseInt(String.valueOf(coordinate.charAt(0)))-1;

        switch (coordinate.charAt(1)){
            case 'a'-> coordinateInt[1] = 0;
            case 'b'-> coordinateInt[1] = 1;
            case 'c'-> coordinateInt[1] = 2;
            case 'd'-> coordinateInt[1] = 3;
            case 'e'-> coordinateInt[1] = 4;
            case 'f'-> coordinateInt[1] = 5;
            case 'g'-> coordinateInt[1] = 6;

        }

        return coordinateInt;
    }

    private void passiveMoney() {

        addMoney(3, true);
        addMoney(3, false);
    }

    //funcion que edita matriz de troops para saber donde avanza la tropa
    private void editMatrix(int[] coordinates, Troop troop) {

        System.out.println("editmatrix");
        //eliminamios posiciondel restro
        if(troop.getLastCoordinate()[0] != -1) {
            matrixTroops[troop.getLastCoordinate()[0]][troop.getLastCoordinate()[1]] = null;
        }

        troop.setLastCoordinate(coordinates);

        //actualizar posicion
        matrixTroops[coordinates[0]][coordinates[1]] = troop;

        //guradar lista de cambio sde tropa rastro
    }

    public String[][][] updateBoard() {
        String[][][] board = new String[BoardView.ROWS][BoardView.COLUMNS][3];

        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if(matrixTroops[i][j] == null) {
                    board[i][j] = null;
                }
                else {
                    board[i][j][0] = matrixTroops[i][j].getName();
                    board[i][j][1] = String.valueOf(matrixTroops[i][j].getRank());
                    board[i][j][2] = String.valueOf(matrixTroops[i][j].isPlayer());
                }
            }
        }

        return board;
    }




}

