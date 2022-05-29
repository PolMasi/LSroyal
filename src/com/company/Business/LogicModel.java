package com.company.Business;

import com.company.Business.Entities.Defensive;
import com.company.Business.Entities.Offensive;
import com.company.Business.Entities.Tower;
import com.company.Business.Entities.Troop;
import com.company.Persistence.GameDAO;
import com.company.Persistence.UserDAO;
import com.company.Presentation.Views.BoardView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * La logica de la partida del jugador y de la cpu
 */
public class LogicModel implements Runnable {
    private ArrayList<Offensive> listOffensive;
    private ArrayList<Defensive> listDefensive;
    private GameDAO gamedao;
    private UserDAO userDAO;

    private int counter;
    private int computerHealth;
    private int computerMoney;
    private Random selectTroop;

    private int userHealth;
    private int userMoney;

    //matriz de posicion de tropas
    private Troop[][] matrixTroops;
    private ArrayList <String [][][]>  repeatMatrix;
    private Iterator <String [][][]> repeatIterator;

    /**
     * getter del diner actual
     * @return el diner actual
     */
    public int getUserMoney() {
        return userMoney;
    }

    /**
     * Contructor de la logica de la partida
     * @param listOffensive arraylsit amb la llista de tropes ofensives
     * @param listDefensive arraylsit amb la llista de tropes defensives
     * @param gamedao control del DAO del joc
     * @param userDAO control del DAO del usuari
     */
    public LogicModel(ArrayList<Offensive> listOffensive, ArrayList<Defensive> listDefensive, GameDAO gamedao, UserDAO userDAO) {
        this.listOffensive = listOffensive;
        this.listDefensive = listDefensive;
        this.gamedao = gamedao;
        this.userDAO = userDAO;
        this.selectTroop = new Random();
        startGame();
    }

    /**
     * Posar tropa defensiva
     * @return un array de strings amb la info de la tropa defensiva
     */
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

    /**
     * Posar tropa ofensiva
     * @return un array de strings amb la info de la tropa ofensiva
     */
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


    /**
     * Funcio pel començament de la partida
     */
    public void startGame() {

        computerMoney = 5;
        computerHealth = 4000;
        userMoney = 5;
        userHealth = 4000;
        matrixTroops = new Troop[BoardView.ROWS][BoardView.COLUMNS];
        invokeTowers();

    }

    /**
     * invocar les tropes a la partida
     */
    public void invokeTowers(){
        Troop tower = new Tower("torres", 1000,0,2, 300);

        for (int i = 0; i < BoardView.COLUMNS; i++) {
            Tower t = new Tower(tower);
            t.setPlayer(false);
            t.setLastCoordinate(new int[]{-1,-1});
            matrixTroops[0][i] = t;
        }

        for (int i = 0; i < BoardView.COLUMNS; i++) {
            Tower t = new Tower(tower);
            t.setPlayer(true);
            t.setLastCoordinate(new int[]{-1,-1});
            matrixTroops[7][i] = t;
        }

    }

    /**
     *
     * control de la tropa invocada
     * @param numCard la carta invocada
     * @param type el tipus de carta
     * @param player el tipo de jugador
     * @param coords les cordinades de la torpa
     * @return si o no
     */
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

    /**
     * selecionar una carta a usar
     * @param numCard la carta a invocar
     * @param type el tipus de carta
     * @return si o no pels diners
     */
    public boolean canSelectTroop(int numCard, boolean type) {
        if(type) {
            return listDefensive.get(numCard-1).getCost() <= userMoney;
        }
        return listOffensive.get(numCard-1).getCost() <= userMoney;
    }

    /**
     * Obtenir el cost de les cartes
     * @param numCard la carta demanda
     * @param type el tipus de carta
     * @return
     */
    public int getCost(int numCard, boolean type) {
        if(type) {
            return listDefensive.get(numCard-1).getCost();
        }
        return listOffensive.get(numCard-1).getCost();
    }

    /**
     * incrementar el diner per poder invocar mes tropes
     * @param money enter amb el diner actual
     * @param player si es la CPU o el jugador
     */
    private void addMoney(int money, boolean player) {

        if(player) {
            userMoney += money;
        }
        else {
            computerMoney += money;
        }
    }

    /**
     * Gastarse el dinero
     * @param numCard el numero de carta seleccionat
     * @param type el tipo de carta
     * @param player la CPU o el jugador
     */
    public void spendMoney(int numCard, boolean type, boolean player) {
        int cost;

        cost = getCost(numCard, type);
        addMoney(-cost, player);

    }

    /**
     * fer corre la partida
     */
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
           invokeDefenseCPU();
           moveTroops();
        }

        if(counter == 4){
            invokeAtackCPU();
            passiveMoney();
        }


    }

    /**
     * controla el moviment de les cartes
     */
    synchronized void moveTroops() {
        final Troop[][] troop = new Troop[BoardView.ROWS][BoardView.COLUMNS];

        for (int i = 1; i < BoardView.ROWS - 1; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                troop[i][j] = matrixTroops[i][j];
            }
        }

        for (int i = 1; i < BoardView.ROWS - 1; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if (troop[i][j] != null && matrixTroops[i][j] != null) {
                    //System.out.println("matrix: "+i+j+" "+matrixTroops[i][j]);
                    editMatrix(matrixTroops[i][j].move(matrixTroops), matrixTroops[i][j]);
                }
            }
        }
    }

    /**
     * Invocar tropes defensives de la CPU
     */
    public void invokeDefenseCPU() {
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

    /**
     * Invocar tropes ofensives de la CPU
     */
    public void invokeAtackCPU(){
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

    /**
     * obtenir la posició actual de les tropes
     * @param x varible del eje de abcisas
     * @param y variable del eje de ordenadas
     * @return las coordinadas
     */
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

    /**
     * getter de la vida del ordinador
     * @return la vida de la CPU
     */
    public int getComputerHealth() {
        return computerHealth;
    }

    /**
     * getter de la vida del jugador
     * @return la vida del jugador
     */
    public int getUserHealth() {
        return userHealth;
    }

    /**
     * obnteir la condirnada actual
     * @param coordinate les coordinades
     * @return un enter amb les cooridnades
     */
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

    /**
     * Afegir una quantitat de diner
      */
    private void passiveMoney() {

        addMoney(2, true);
        addMoney(2, false);
    }

    /**
     *moment de lluita de tropes
     * @param coordinates array de enters amb les posicions
     * @param troop la variable de la tropa
     */
    private void fight (int[] coordinates, Troop troop) {

        matrixTroops[coordinates[0]][coordinates[1]].setCurrentHealth(troop.getDamage());
        System.out.println(matrixTroops[coordinates[0]][coordinates[1]].getCurrentHealth()+ "vida");

        if (matrixTroops[coordinates[0]][coordinates[1]].getTroopName() == "torres") {
            if (matrixTroops[coordinates[0]][coordinates[1]].isPlayer()) {
                userHealth -= troop.getDamage();
            }
            else {
                computerHealth -= troop.getDamage();
            }
        }
    }

    /**
     * funcion que edita matriz de troops para saber donde avanza la tropa
     * @param coordinates  array de enters amb les posicions
     * @param troop la variable de la tropa
     */
   synchronized void editMatrix(int[] coordinates, Troop troop) {

            if (troop == null) {
                return;
            }

            //eliminamios posiciondel restro
            if (troop.getLastCoordinate()[0] != -1) {
                matrixTroops[troop.getLastCoordinate()[0]][troop.getLastCoordinate()[1]] = null;
            }

            if (troop.isFight()) {

                fight(coordinates,troop );
                if(matrixTroops[coordinates[0]][coordinates[1]].getCurrentHealth() <= 0) {
                    //si esta muerta la elimnamos
                    matrixTroops[coordinates[0]][coordinates[1]] = null;

                }
                matrixTroops[troop.getLastCoordinate()[0]][troop.getLastCoordinate()[1]] = troop;

            } else {
                troop.setLastCoordinate(coordinates);
                //actualizar posicion
                matrixTroops[coordinates[0]][coordinates[1]] = troop;
            }

        //gamedao.matrixToJson(matrixTroops);
    }

    /**
     * aray triple per actualizar el taulell amb tropes
     * @return el taulell
     */
    public String[][][] updateBoard() {

        String[][][] board = new String[BoardView.ROWS][BoardView.COLUMNS][4];

        for (int i = 0; i < BoardView.ROWS; i++) {
            for (int j = 0; j < BoardView.COLUMNS; j++) {
                if(matrixTroops[i][j] == null) {
                    board[i][j] = null;
                }
                else {
                    board[i][j][0] = matrixTroops[i][j].getTroopName();
                    board[i][j][1] = String.valueOf(matrixTroops[i][j].getRank());
                    board[i][j][2] = String.valueOf(matrixTroops[i][j].isPlayer());
                    board[i][j][3] = String.valueOf(matrixTroops[i][j].getCurrentHealth());
                }
            }
        }

        gamedao.matrixToJson(board);
        return board;
    }

    /**
     * guardar la partida
     * @param gameName el nom de la partida
     * @param win enter per si ha guanyat o no
     * @return la partida jugada guardada
     */
    public boolean saveGame(String gameName, int win){

      return  gamedao.saveGame(userDAO.userID(), gameName, win);


    }

    /**
     * si el usuari ha guanyat, s'afegeix un victoria
     */
    public void addVictory() {

        userDAO.addVictory(userDAO.userID());
    }

    /**
     * afegir una partdia
     */
    public void addGame() {

        userDAO.addGame(userDAO.userID());
    }

    /**
     * obtenir una partida
     * @return la posicó on es guarda la partida
     */
    public String [][] getGames(){

        ArrayList<String[]> list = gamedao.getSavedGames(userDAO.userID());

        String[][] matrix = new String[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            matrix[i] = list.get(i);
        }
        return matrix;


    }

    /**
     * getter per obtenir una partida per reproduirla
     * @param gameID el id del jugador
     */
    public void getReplayGame(int gameID){

        repeatMatrix = gamedao.getReplayGame(gameID);
        repeatIterator = repeatMatrix.iterator();

    }

    /**
     * obnteir la reproduccio del moviment de la repeticio
     * @return moviment de la repeticio
     */
    public String [][][] getReplayMove(){

        if(repeatIterator.hasNext()){

            return repeatIterator.next();

        }

        return null;


    }
}

