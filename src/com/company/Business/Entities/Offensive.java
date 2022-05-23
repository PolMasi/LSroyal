package com.company.Business.Entities;

public class Offensive extends Troop {

    public Offensive(String name, int health, int cost, int rank, int damage) {
        super(name, health, cost, rank, damage);
    }

    public Offensive(Troop troop) {
        super(troop);
    }

    public synchronized int[] move(Troop[][] matrixTroops) {
        int[] position = this.getLastCoordinate();
        int direction;

        this.setFight(false);

        if(isPlayer()) {
            direction = -1;
        }
        else {
            direction = 1;
        }

        //System.out.println(getTroopName()+": last position"+position[0]+position[1]);

        if(position[0]+getRank()*direction <= 0 || position[0]+getRank()*direction >= 7) {
            //System.out.println(getTroopName()+"final");
            return position;
        }

        for (int i = 1; i < getRank()+1; i++) {
            if(position[0]+i*direction >= 0 || position[0]+i*direction <= 7) {

                //System.out.println(getTroopName()+": dentro1"+i);
                if(matrixTroops[position[0]+i*direction][position[1]] != null) {

                    if(matrixTroops[position[0]+i*direction][position[1]].isPlayer() != this.isPlayer()) {

                        this.setFight(true);

                        return new int[]{position[0]+i*direction,position[1]};

                    }

                    //System.out.println(getTroopName()+": stop"+i);
                    return position;
                }
            }
        }



        //System.out.println(getTroopName()+": new position"+(position[0]+direction)+position[1]);

        return new int[]{position[0] + direction, position[1]};

    }

    @Override
    public void run() {

    }
}
