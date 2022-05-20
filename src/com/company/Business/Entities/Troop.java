package com.company.Business.Entities;

public class Troop {
    private String name;
    private int health;
    private int cost;
    private int rank;
    private int currentHealth;
    private int[] lastCoordinate;
    private boolean player;

    public Troop(String name, int health, int cost, int rank) {
        this.name = name;
        this.health = health;
        this.cost = cost;
        this.rank = rank;
        this.currentHealth = this.health;
        this.lastCoordinate = new int[2];
        this.lastCoordinate[0] = -1;
    }

    public int[] move(Troop[][] matrixTroops) {
        return lastCoordinate;
    }


    public int getCost() {
        return cost;
    }

    public String getName() {
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

}
