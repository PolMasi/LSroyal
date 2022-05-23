package com.company.Business.Entities;

public class Tower extends Troop{

    public Tower(String name, int health, int cost, int rank, int damage) {
        super(name, health, cost, rank, damage);
    }

    public Tower(Troop troop) {
        super(troop);
    }
    public int[] move(Troop[][] matrixTroops) {
        return getLastCoordinate();
    }
}
