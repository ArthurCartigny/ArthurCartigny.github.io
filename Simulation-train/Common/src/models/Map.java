package models;
import fileReader.LectureFichier;

import java.io.Serializable;
import java.util.List;
/**
 * Représente le tableau principal du programme
 * Contient les trains et le tableau de rails selon un fichier map
 * @see LectureFichier
 * @see Train
 * @see Rail
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Map implements Serializable {

    //Taille en static pour etre sur que cela change pas, initalisées dans la méthode lireFichier
    /**
     * Taille du tableau en longueur
     */
    public int tailleX ;
    /**
     * Taille du tableau en largeur
     */
    public int tailleY ;

    /**
     * Tableau principal de la map
     * Il est à deux dimensions afin de créer une map rectangulaire
     * Reçoit des CaseMap pour pouvoir jouer avec le polymorphisme
     * @see CaseMap
     * @see Rail
     */
    public CaseMap[][] map;

    /**
     * Liste contenant les trains circulant sur la map
     * @see Train
     */
    public List<Train> trains;

    /**
     * Constructeur de la classe qui appelle la méthode de récupération de données de la map
     * @see LectureFichier
     * @param reader instance de la classe LectureFichier permettant de récupérer les données de la map
     */
    public Map(LectureFichier reader) {
        lireFichier(reader);
    }

    /**
     * Constructeur par défaut
     * Crée pour pouvoir effectuer les tests unitaires sur cette classe
     */
    public Map(){

    }

    /**
     * Récupère les données du fichier map
     * tailleX correspond à la taille de la carte sur le plan horizontal
     * tailleY correspond à la taille de la carte sur le plan vertical
     * map est le tableau de CaseMap et Rail selon la construction via le LectureFichier
     * trains récupère le nombre de trains
     * @param reader une instance de la classe LectureFichier permettant de récupérer les données du fichier map de la taille, des rails et des trains
     */
    public void lireFichier(LectureFichier reader){

        tailleX = reader.getTailleX();
        System.out.println("Taille X: " + tailleX);
        tailleY = reader.getTailleY();
        System.out.println("Taille Y: " + tailleY);
        map = reader.getMap();
        trains = reader.getTrains();

    }

    /**
     * Mets à jour les cases de la carte selon si une case est occupée par un train ou non
     * Parcours l'ensemble des cases du tableau de case
     * Retire toutes les occupations
     * Si la case est un Rail, on compare si les coordonnées de la case correspondent à une partie du train
     * @see Rail
     * @see Train
     * On regarde dans le tableau du train si la locomotive correspond aux coordonnées puis on vérifie chaque wagon
     * @see PartieDuTrain
     * @see Locomotive
     * @see Wagon
     * Si les coordonnées correspondent, on ajoute une occupation sur la case
     *
     */
    public void updateMap() {
        for (int x = 0; x < tailleX; x++) {
            for (int y = 0; y < tailleY; y++) {
                if (map[x][y] != null && map[x][y] instanceof Rail) {
                    Rail casetmp = (Rail) map[x][y];
                    casetmp.retirerOccupation();
                    for (Train train : trains) {
                        if (train.locomotive.positionX == casetmp.positionX && train.locomotive.positionY == casetmp.positionY) {
                            casetmp.ajouterOccupation(train.locomotive);
                            break;
                        }
                        else
                        {
                            for(Wagon wagon : train.wagons){
                                if(wagon != null){
                                    if(wagon.positionX == casetmp.positionX && wagon.positionY == casetmp.positionY){
                                        casetmp.ajouterOccupation(wagon);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
