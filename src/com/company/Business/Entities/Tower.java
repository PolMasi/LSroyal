package com.company.Business.Entities;

/**
 * Control de la funcionalitat de les torres
 */
public class Tower extends Troop{
    /**
     * Constructor de les torres
     * @param name string del nom
     * @param health enter amb la vida de la torre
     * @param cost coste
     * @param rank alcance de la tropa
     * @param damage daño
     */
    public Tower(String name, int health, int cost, int rank, int damage) {
        super(name, health, cost, rank, damage);
    }

    /**
     * funcionament de la tropa torre
     * @param troop conté la informació de la tropa tower
     */
    public Tower(Troop troop) {
        super(troop);
    }

    /**
     * Posicionament de la torre
     * @param matrixTroops array doble amb la posició de les torres
     * @return la posició actual
     */
    public int[] move(Troop[][] matrixTroops) {
        return getLastCoordinate();
    }
}
