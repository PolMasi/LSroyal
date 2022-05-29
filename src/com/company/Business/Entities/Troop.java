package com.company.Business.Entities;

/**
 * Engloba als tres tipus de tropa
 */
public abstract class Troop {
    private String name;
    private int health;
    private int cost;
    private int rank;
    private int damage;
    private int currentHealth;
    private int[] lastCoordinate;
    private boolean player;
    private boolean fight;

    /**
     * Contructor per totes les tropes
     * @param name nom de la tropa
     * @param health vida restant de la tropa
     * @param cost  cost de la tropa
     * @param rank alcance de la tropa
     * @param damage daño de la tropa
     */
    public Troop(String name, int health, int cost, int rank, int damage) {
        this.name = name;
        this.health = health;
        this.cost = cost;
        this.rank = rank;
        this.currentHealth = this.health;
        this.lastCoordinate = new int[2];
        this.lastCoordinate[0] = -1;
        this.fight = false;
        this.damage = damage;
    }

    /**
     * Constructor de la informació tropes per la partida
     * @param troop variable amb la informació de la tropa
     */
    public Troop(Troop troop) {
        this.name = troop.name;
        this.health = troop.health;
        this.cost = troop.cost;
        this.rank = troop.rank;
        this.currentHealth = this.health;
        this.lastCoordinate = new int[2];
        this.lastCoordinate[0] = -1;
        this.fight = false;
        this.damage = troop.damage;

    }

    /**
     * Control del moviment de les tropes
     * @param matrixTroops array doble de la posició al taulell de la tropa
     * @return la ultima posició
     */
    public synchronized int[] move(Troop[][] matrixTroops) {
        return lastCoordinate;
    }

    /**
     * getter del cost de la tropa
     * @return el cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * getter del nom de la tropa
     * @return el nom
     */
    public String getTroopName() {
        return name;
    }

    /**
     * getter de la vida actual
     * @return la vida actual
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * getter de la pisció actual
     * @return posició actual
     */
    public int[] getLastCoordinate() {
        return lastCoordinate;
    }

    /**
     * setter de la ultima posició
     * @param lastCoordinate la utlima conrdinada
     */
    public void setLastCoordinate(int[] lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
    }

    /**
     * boolean del jugador
     * @return el jugador
     */
    public boolean isPlayer() {
        return player;
    }

    /**
     * setter del jugador
     * @param player el valor del jugador
     */
    public void setPlayer(boolean player) {
        this.player = player;
    }

    /**
     * getter del rang de daño
     * @return el rang de daño
     */
    public int getRank() {
        return rank;
    }

    /**
     * setter de la lluita
     * @param fight la llutia de les cartes
     */
    public void setFight(boolean fight) {
        this.fight = fight;
    }

    /**
     * Boolean si estan lluitan
     * @return si o no
     */
    public boolean isFight() {
        return fight;
    }

    /**
     * setter de la vida actual
     * @param damage el daño revicido
     */
    public void setCurrentHealth(int damage) {
        this.currentHealth -= damage;
    }

    /**
     * getter del daño
     * @return el daño recivido
     */
    public int getDamage() {
        return damage;
    }
}
