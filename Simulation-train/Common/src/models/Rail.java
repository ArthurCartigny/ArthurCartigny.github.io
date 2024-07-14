package models;

import java.io.Serializable;

//Case de la map qui fait parti du chemin de fer

/**
 * Représente une case de la map surlaquelle une partie d'un train pourra circuler
 * @see Train
 * @see PartieDuTrain
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Rail extends CaseMap implements Serializable {
    /**
     * Caractère qui représente le rail
     */
    Sens sens;
    boolean pivote= false;
    /**
     * Partie du train se trouvant sur le rail
     */
    public PartieDuTrain train;

    /**
     * Constructeur de la classe
     * @param x coordonnée en x du rail
     * @param y coordonnée en y du rail
     * @param sens caractère représentant le rail
     * @see Sens
     */
    public Rail(int x, int y, Sens sens,boolean b) {
        super();
        this.positionX = x;
        this.positionY = y;
        this.sens = sens;
        this.pivote = b;
    }

    /**
     * Getter permettant de récupérer le sens du rail
     * @return renvoi le sens du rail
     */
    public Sens getSens() {
        return sens;
    }

    /**
     * ajoute une occupation par une partie du train au rail
     * @param train occupant du rail, il a les mêmes coordonnées que le rail
     */
    public void ajouterOccupation(PartieDuTrain train){
        if(train.positionX == this.positionX && train.positionY == this.positionY)
        this.train = train;
    }

    /**
     * retire la partie du train du rail
     * @see PartieDuTrain
     */
    public void retirerOccupation(){
        this.train = null;
    }

}
