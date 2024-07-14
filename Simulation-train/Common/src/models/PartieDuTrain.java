package models;

import java.io.Serializable;

/**
 * Représente une partie d'un train qui sera contenu dans un tableau de la classe Train
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 * @see Train
 * Classe mère de la locomotive et du wagon
 * @see Locomotive
 * @see Wagon
 */
public class PartieDuTrain implements Serializable {
    /**
     * Coordonnée en x de la position de la partie du train
     */
    public int positionX;
    /**
     * Coordonnée en y de la position de la partie du train
     */
    public int positionY;
    /**
     * Direction de la partie du train
     */
    public Direction directionDuTrain;
    /**
     * Rail surlequel se trouve le train
     */
    Rail emplacement;

    /**
     * Setter de l'emplacement
     * utilisée dans les tests unitaires
     * @param rail rail qui doit être mis à l'emplacement
     */
   public void setEmplacement(Rail rail)
    {
        emplacement = rail;
    }

    /**
     * Constructeur de la classe
     *
     * @param x   coordonnée en x de la partie du train
     * @param y   coordonnée en y de la partie du train
     * @param dir direction de base de la partie du train
     */
    public PartieDuTrain(int x, int y, Direction dir) {
        positionX = x;
        positionY = y;
        directionDuTrain = dir;
    }

    /**
     * Déplace la partie du train sur la carte
     * Implique un changement des coordonnées x et y de la partie du train via la méthode avancerLeTrain()
     * Fait appel à un changement de direction via la méthode changerDirection()
     *
     * @param map tableau dans lequel se trouve les rails et les cases vides sur lesquelles se déplacent les trains
     */
    public void deplacement(Map map) {
        emplacement = (Rail) map.map[positionX][positionY];

        if (emplacement != null)
            changerDirection();

        //avancerLeTrain();
    }


    /**
     * Change la direction du train si le train se trouve à un virage
     *
     * @see Direction
     * @see Sens
     */
    public void changerDirection() {

        switch (directionDuTrain) {
            case Left:
                if (emplacement.getSens() == Sens.MontéeDroite) {
                    directionDuTrain = Direction.Bottom;
                } else if (emplacement.getSens() == Sens.DescenteDroite) {
                    directionDuTrain = Direction.Top;
                }
                break;

            case Right:
                if (emplacement.getSens() == Sens.MontéeGauche) {
                    directionDuTrain = Direction.Bottom;
                } else if (emplacement.getSens() == Sens.DescenteGauche) {
                    directionDuTrain = Direction.Top;
                }
                break;

            case Top:
                if (emplacement.getSens() == Sens.MontéeGauche) {
                    directionDuTrain = Direction.Left;
                } else if (emplacement.getSens() == Sens.MontéeDroite) {
                    directionDuTrain = Direction.Right;
                }
                break;

            case Bottom:
                if (emplacement.getSens() == Sens.DescenteGauche) {
                    directionDuTrain = Direction.Left;
                } else if (emplacement.getSens() == Sens.DescenteDroite) {
                    directionDuTrain = Direction.Right;
                }
                break;
        }
    }

    /**
     * Fait avancer le train en changeant les coordonnées de la partie selon la direction du train
     */
    public void avancerLeTrain() {
        if (directionDuTrain == Direction.Left) {
            positionX -= 1;
        } else if (directionDuTrain == Direction.Right) {
            positionX += 1;
        } else if (directionDuTrain == Direction.Top) {
            positionY -= 1;
        } else if (directionDuTrain == Direction.Bottom) {
            positionY += 1;
        }
    }
}
