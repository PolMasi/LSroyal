package com.company.Business.Entities;

public class Tower extends Troop{

    public Tower(String name, int health, int cost, int rank) {
        super(name, health, cost, rank);
    }

    public Tower(Troop troop) {
        super(troop);
    }
    public int[] move(Troop[][] matrixTroops) {
        return getLastCoordinate();
    }
}
