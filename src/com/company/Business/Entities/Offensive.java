package com.company.Business.Entities;

/**
 * Control de les tropes ofensives
 */
public class Offensive extends Troop {

    public Offensive(String name, int health, int cost, int rank, int damage) {
        super(name, health, cost, rank, damage);
    }

    /**
     *  Funcionalitat de les tropes ofesnisves
     * @param troop conté la informaició de la tropa
     */
    public Offensive(Troop troop) {
        super(troop);
    }

    /**
     * Control del moviment  y la posició de les troves ofenvises
     * @param matrixTroops matriu de les tropes ofensives amb tota la seva infomració
     * @return la posció y direcció de la tropa
     */
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



        if(position[0]+getRank()*direction < 0 || position[0]+getRank()*direction > 7) {

            return position;
        }

        for (int i = 1; i < getRank()+1; i++) {
            if(position[0]+i*direction >= 0 || position[0]+i*direction <= 7) {

                if(matrixTroops[position[0]+i*direction][position[1]] != null) {
                    if(matrixTroops[position[0]+i*direction][position[1]].isPlayer() != this.isPlayer()) {
                        this.setFight(true);
                        return new int[]{position[0]+i*direction,position[1]};
                    }
                    return position;
                }
            }
        }



        return new int[]{position[0] + direction, position[1]};

    }

}
