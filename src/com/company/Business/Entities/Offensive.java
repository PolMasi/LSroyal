package com.company.Business.Entities;

public class Offensive extends Troop {

    public Offensive(String name, int health, int cost, int rank) {
        super(name, health, cost, rank);
    }

    public int[] move(Troop[][] matrixTroops) {
        int[] position = this.getLastCoordinate();
        int direction;

        if(isPlayer()) {
            direction = -1;
        }
        else {
            direction = 1;
        }

        System.out.println("last position"+position[0]+position[1]);

        //Si hay alguien en rango de ataque no se mueve
        if(matrixTroops[position[0]+getRank()*direction][position[1]] != null) {
            System.out.println("dentro");
            return position;
        }

        System.out.println("new position"+(position[0]+1)+position[1]);
        //avance para delante
        return new int[]{position[0] + direction, position[1]};

    }
}
