package models;

import java.io.Serializable;

/**
 * Représente une partie du train qui n'est pas la première
 * @see Train
 * Hérite de PartieDuTrain
 * @see PartieDuTrain
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Wagon extends PartieDuTrain implements Serializable {
    /**
     * Constructeur de la classe
     * @param x coordonnée en x du wagon
     * @param y coordonnée en y du wagon
     * @param dir direction de base du wagon
     */
    public Wagon(int x, int y, Direction dir)
    {
        super(x, y, dir);
    }
}
