package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Business.Entities.Tower;
import com.company.Business.Entities.Troop;
import com.company.Persistence.GameDAO;
import com.company.Presentation.Views.BoardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LogicModel implements Runnable {
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;
    private GameDAO gamedao;

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

    public LogicModel(ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive, GameDAO gamedao) {
        this.listOffensive = listOffensive;
        this.listDefensive = listDefensive;
        this.gamedao = gamedao;
        this.selectTroop = new Random();
        startGame();
    }

    public String[] setDefensiveCards() {
        String[] defString = new String[listDefensive.size()*3];
        int j = 0;

        for (int i = 0; i < listDefensive.size(); i++) {
            defString[j] = listDefensive.get(i).getTroopName();
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
            offString[j] = listOffensive.get(i).getTroopName();
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
            return listDefensive.get(numCard).getTroopName();
        }
        return listOffensive.get(numCard).getTroopName();
    }

    public void startGame() {
        computerMoney = 5;
        computerHealth = 100;
        userMoney = 5;
        userHealth = 100;
        matrixTroops = new Troop[BoardView.ROWS][BoardView.COLUMNS];
        invokeTowers();
    }
    public void invokeTowers(){
        for (int i = 0; i < BoardView.COLUMNS; i++) {
            Troop tower = new Tower("tower", 1000,0,1, 300);
            tower.setPlayer(true);
            tower.setLastCoordinate(new int[]{-1,-1});
            matrixTroops[0][i] = tower;
        }
        for (int i = 0; i < BoardView.COLUMNS; i++) {
            Troop tower = new Tower("tower", 1000,0,1, 300);
            tower.setPlayer(false);
            tower.setLastCoordinate(new int[]{-1,-1});
            matrixTroops[7][i] = tower;
        }

    }

    public boolean invokeTroop(int numCard, boolean type, boolean player,String coords) {

        Troop troop;
        int[] coordinates = getIntCoordinate(coords);

        if(type) {
            troop = new Defensive(listDefensive.get(numCard-1));
        }
        else {
            troop = new Offensive(listOffensive.get(numCard-1));
        }

        troop.setLastCoordinate(new int[]{-1, -1});

        if(matrixTroops[coordinates[0]][coordinates[1]] != null) {
            return false;
        }

        if (player) {
            System.out.println(numCard+listDefensive.get(numCard-1).getTroopName());
            if (coords.contains("5") || coords.contains("6") || coords.contains("7") || coords.contains("8")) {
                troop.setPlayer(player);
                System.out.println(troop.getTroopName()+coordinates);
                editMatrix(coordinates, troop);

                return true;

            } else {
                return false;
            }
        }

        troop.setPlayer(player);
        editMatrix(coordinates, troop);

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
        System.out.println(counter);
       counter++;
        if (userHealth == 0){
            //has perdido
        }

        if(computerHealth == 0) {
            // has ganado
        }

        if(counter == 3) {
           defenseTroop();
           moveTroops();
        }

        if(counter == 4){
            invokeComputerAtack();
            passiveMoney();
        }


    }

    synchronized void moveTroops() {
        final Troop[][] troop = new Troop[BoardView.ROWS][BoardView.COLUMNS];
        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                troop[i][j] = matrixTroops[i][j];
            }
        }
        for (int i = 1; i < BoardView.ROWS - 1; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if (troop[i][j] != null) {
                    //System.out.println("matrix: "+i+j+" "+matrixTroops[i][j]);
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
       //     Collections.shuffle(listDefensive);
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
        //counter++;

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
          //  Collections.shuffle(listDefensive);
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

    private void fight (int[] coordinates, Troop troop) {

        matrixTroops[coordinates[0]][coordinates[1]].setCurrentHealth(troop.getDamage());
        System.out.println(matrixTroops[coordinates[0]][coordinates[1]].getCurrentHealth()+ "vida");
    }

    //funcion que edita matriz de troops para saber donde avanza la tropa
   synchronized void editMatrix(int[] coordinates, Troop troop) {


       //System.out.println("edit matrix"+coordinates[0]+coordinates[1]);



            //eliminamios posiciondel restro
            if (troop.getLastCoordinate()[0] != -1) {
                matrixTroops[troop.getLastCoordinate()[0]][troop.getLastCoordinate()[1]] = null;
            }

            if (troop.isFight()) {

                System.out.println(troop.getTroopName()+"is fiting");
                    fight(coordinates,troop );

                if(matrixTroops[coordinates[0]][coordinates[1]].getCurrentHealth() <= 0) {
                    //si esta muerta la elimnamos
                    matrixTroops[coordinates[0]][coordinates[1]] = null;
                    System.out.println("muerto");
                }

                System.out.println(troop.getDamage());
                matrixTroops[troop.getLastCoordinate()[0]][troop.getLastCoordinate()[0]] = troop;


            } else {

                troop.setLastCoordinate(coordinates);

                //actualizar posicion
                matrixTroops[coordinates[0]][coordinates[1]] = troop;
            }

        gamedao.matrixToJson(matrixTroops);
    }

    public String[][][] updateBoard() {
        String[][][] board = new String[BoardView.ROWS][BoardView.COLUMNS][3];

        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if(matrixTroops[i][j] == null) {
                    board[i][j] = null;
                }
                else {
                    board[i][j][0] = matrixTroops[i][j].getTroopName();
                    board[i][j][1] = String.valueOf(matrixTroops[i][j].getRank());
                    board[i][j][2] = String.valueOf(matrixTroops[i][j].isPlayer());
                }
            }
        }

        return board;
    }




}

