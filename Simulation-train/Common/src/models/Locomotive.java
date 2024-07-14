package models;

import java.io.Serializable;

/**
 * Représente la première partie du train
 * Cette classe hérite de PartieDuTrain
 * @see PartieDuTrain
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Locomotive extends PartieDuTrain implements Serializable {

    /**
     * Constructeur de la classe
     * La locomotive est la première partie du train
     * Fait appel au constructeur de la classe mère de PartieDuTrain
     *
     * @param x   coordonnée en x de l'emplacement de la locomotive
     * @param y   coordonnée en y de l'emplacement de la locomotive
     * @param dir direction de base de la locomotive
     */
    public Locomotive(int x, int y, Direction dir) {
        super(x, y, dir);

    }

    /**
     * vérifie si la case en face de la locomotive est libre
     * @param map tableau sur lequel se déplace la locomotive
     * @return renvoi une valeur booléenne si la case suivante est libre (false) ou non (true)
     */
    public boolean checkCollisions(Map map) {
        Rail rail = calculDuProchainRail(map);
        if(rail != null){
            if(rail.train == null)
            {
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }
    /**
     * Calcule le rail suivant
     * @param mainMap tableau dans lequel sont contenus toutes les cases et les rails
     * @see CaseMap
     * @see Rail
     * @return renvoie le rail suivant
     */
    public Rail calculDuProchainRail(Map mainMap){
        switch(directionDuTrain) {
            case Bottom:
                if (mainMap.map[positionX][positionY + 1] != null && mainMap.map[positionX][positionY + 1].getClass().getName().equals("models.Rail")) {
                    Rail tmp = (Rail) mainMap.map[positionX][positionY + 1];
                    return tmp;
                }

            case Top:
                if (mainMap.map[positionX][positionY - 1] != null && mainMap.map[positionX][positionY - 1].getClass().getName().equals("models.Rail")) {
                    Rail tmp = (Rail) mainMap.map[positionX][positionY - 1];
                    return tmp;
                }

            case Left:
                if (mainMap.map[positionX - 1][positionY] != null && mainMap.map[positionX - 1][positionY].getClass().getName().equals("models.Rail")) {
                    Rail tmp = (Rail) mainMap.map[positionX - 1][positionY];
                    return tmp;
                }

            case Right:
                if (mainMap.map[positionX + 1][positionY] != null && mainMap.map[positionX + 1][positionY].getClass().getName().equals("models.Rail")) {
                    Rail tmp = (Rail) mainMap.map[positionX + 1][positionY];
                    return tmp;
                }

            default:
                return null;
        }
    }
}
