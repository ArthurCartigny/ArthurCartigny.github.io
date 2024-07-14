package models;

import fileReader.LectureFichier;

import java.io.Serializable;

/**
 * Représente le train complet
 * Constituée d'une locomotive et de wagons
 * @see PartieDuTrain
 * @see Locomotive
 * @see Wagon
 * Circule sur une map
 * @see Map
 * Crée par la lecture du fichier map.map
 * @see LectureFichier
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Train implements Serializable {

    public int tailleTrain;
    public Wagon [] wagons;
    public Locomotive locomotive;
    Map mainMap;
    public Direction directionDeBase;
    public boolean Avance = true;

    /**
     * Constructeur de la classe
     * @param x coordonnée en x de la locomotive, contenue dans le fichier map.map
     * @param y coordonnée en y de la locomotive contenue dans le fichier map.map
     * @param NbWagons nombre de wagons, lue dans le fichier map.map
     * @param dir direction de base du train, lue dans le fichier map.map
     * @see LectureFichier
     */
    public Train(int x, int y, int NbWagons, Direction dir) {
        //Création de la locomotive
        locomotive = new Locomotive(x, y, dir); //Paramètres lus dans la lecture du fichier
        wagons = new Wagon[NbWagons-1];   //Nombre de wagons lus dans la lecture du fichier
        tailleTrain = NbWagons;
        directionDeBase = dir;
    }

    /**
     * Associe la map au train et crée les wagons sur la map
     * @param map map surlaquelle circule le train
     */
    public void demarrerLeTrain(Map map){
        setMap(map);
        creationWagons();
    }

    /**
     * Créer les wagons et déplace les autres parties du train pour éviter qu'ils se retrouvent aux mêmes coordonnées
     * @see Locomotive
     * @see Wagon
     * @see Map
     */
    public void creationWagons()
    {
        //Il faut que la locomotive se déplace tailleDuTrain fois et que à chaque déplacement, il crée un wagon sur sa position initiale puis fait déplacer le wagon qu'il vient de créer
        int positionXInitiale = locomotive.positionX;
        int positionYInitiale = locomotive.positionY;
        Direction directionInitiale = locomotive.directionDuTrain;

        locomotive.deplacement(mainMap);
        locomotive.avancerLeTrain();

        for(int i = 0; i < tailleTrain-1; i++) {
            wagons[i] = new Wagon(positionXInitiale, positionYInitiale, directionInitiale);

            locomotive.deplacement(mainMap);
            locomotive.avancerLeTrain();
            for (Wagon wagon : wagons) {
                if(wagon != null)
                {
                    wagon.deplacement(mainMap);
                    wagon.avancerLeTrain();
                }
            }
            mainMap.updateMap();
        }
    }

    /**
     * Fait déplacer la locomotive et tous les wagons du train puis mets à jour la map
     * @see Locomotive
     * @see Wagon
     * @see Map
     */
    public void deplacementTrain(){

            locomotive.deplacement(mainMap);
            if(!locomotive.checkCollisions(mainMap)) {
                locomotive.avancerLeTrain();
                for (int i = 0; i < tailleTrain-1; i++) {
                    wagons[i].deplacement(mainMap);
                    wagons[i].avancerLeTrain();
                }
            }
            mainMap.updateMap();


    }

    /**
     * Associe la map au train
     * @param map map surlaquelle le train circule
     */
    public void setMap(Map map){
        mainMap = map;
    }

    /**
     * Utilisée pour les tests unitaires
     * @return renvoie la map associée au train
     */
    public Map getMap() {
        return mainMap;
    }
}
