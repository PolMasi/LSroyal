package com.company.Business.Entities;

/**
 * Control de les tropes defensives
 */
public class Defensive extends Troop {

    public Defensive(String name, int health, int cost, int rank, int damage) {
        super(name, health, cost, rank, damage);
    }

    /**
     * Funcionalitat de les tropes defensives
     * @param troop conté la informaició de la tropa
     */
    public Defensive(Troop troop) {
        super(troop);
    }

    /**
     * controla el moviment de les tropes defensives
     * @param matrixTroops array doble amb la informació de les tropes
     * @return la posisció actual de la tropa
     */
    public int[] move(Troop[][] matrixTroops) {

        int[] position = this.getLastCoordinate();
        int direction;

        this.setFight(false);

        if(isPlayer()) {
            direction = -1;
        }
        else {
            direction = 1;
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

        return getLastCoordinate();
    }

}
