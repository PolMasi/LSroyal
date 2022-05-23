package com.company.Business.Entities;

public abstract class Troop extends Thread {
    private String name;
    private int health;
    private int cost;
    private int rank;
    private int damage;
    private int currentHealth;
    private int[] lastCoordinate;
    private boolean player;
    private boolean fight;

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

    public synchronized int[] move(Troop[][] matrixTroops) {
        return lastCoordinate;
    }

    public int getCost() {
        return cost;
    }

    public String getTroopName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int[] getLastCoordinate() {
        return lastCoordinate;
    }

    public void setLastCoordinate(int[] lastCoordinate) {
        this.lastCoordinate = lastCoordinate;
    }

    public boolean isPlayer() {
        return player;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public int getRank() {
        return rank;
    }

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    public boolean isFight() {
        return fight;
    }

    public void setCurrentHealth(int damage) {
        this.currentHealth -= damage;
    }

    public int getDamage() {
        return damage;
    }
}
