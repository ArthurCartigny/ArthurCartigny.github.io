package models;

import java.io.Serializable;


/**
 * Représente les cases de la map
 * @see Map
 * Cette classe peut être la classe mère de la classe Rail
 * @see Rail
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class CaseMap implements Serializable {

    /**
     * Coordonnée sur le plan horizontal de la case sur la map
     */
    public int positionX;
    /**
     * Coordonnée sur le plan vertical de la case sur la map
     */
    public int positionY;

    /**
     * Constructeur de la classe
     * @param x coordonnée en x de la case sur le tableau de la map
     * @param y coordonnée en y de la case sur le tableau de la map
     */
    public CaseMap(int x, int y){
        positionX = x;
        positionY = y;
    }

    /**
     * Constructeur par défaut de la classe
     * Appelée dans le constructeur de la classe héritière Rail
     * @see Rail
     */
    public CaseMap() {

    }
}
