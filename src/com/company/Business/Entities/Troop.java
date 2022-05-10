package com.company.Business.Entities;

public class Troop {
    private String name;
    private int health;
    private int cost;
    private int rank;

    public int getCost() {
        return cost;
    }

    public Troop(String name, int health, int cost, int rank) {
        this.name = name;
        this.health = health;
        this.cost = cost;
        this.rank = rank;
    }
}
