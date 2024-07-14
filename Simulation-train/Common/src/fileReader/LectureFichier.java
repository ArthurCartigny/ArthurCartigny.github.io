package fileReader;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Permet de récupérer un fichier, d'en lire le contenu et de créer les bons éléments (trains et map)
 * @see Train
 * @see Map
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class LectureFichier implements Serializable {
    /**
     * Fichier qui sera lu
     */
    File ficherMap;

    /**
     * Taille en x de la future map
     */
    int tailleX;
    /**
     * Taille en y de la future map
     */
    int tailleY;
    /**
     * Tableau de case de la map
     */
    CaseMap[][] map;
    /**
     * Liste de train
     */
    List<Train> trains;

    /**
     * Getter pour la taille de la map au niveau horizontal
     * @return taille du tableau au niveau horizontal
     */
    public int getTailleX() {
        return tailleX;
    }

    /**
     * Getter pour la taille de la map au niveau vertical
     * @return taille du tableau au niveau vertical
     */
    public int getTailleY() {
        return tailleY;
    }

    /**
     * Getter pour le tableau de cases de la map
     * @return le tableau à deux dimensions de CaseMap
     */
    public CaseMap[][] getMap() {
        return map;
    }

    /**
     * Getter pour la liste de Train
     * @return la liste du train
     */
    public List<Train> getTrains() {
        return trains;
    }


    /**
     * Constructeur de la classe
     * @param path chemin du fichier
     */
    public LectureFichier(String path){
        ficherMap = new File(path);
        lireLeFichier();
    }

    /**
     * Lit le contenu du fichier
     */
    void lireLeFichier(){
        try(BufferedReader br = new BufferedReader(new FileReader(ficherMap))){
            lireTailles(br);
            lireMap(br);
            lireTrains(br);
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Lit la taille de la map
     * Deux premières lignes du fichier
     * @param br pour pouvoir lire le contenu du fichier en maintenant les lignes déjà lues
     * @throws IOException possible d'avoir une erreur lors de la lecture du fichier
     */
    void lireTailles(BufferedReader br) throws IOException {

            //Lecture de la taille
            tailleX = Integer.parseInt(br.readLine());
            //Lecture de la hauteur
            tailleY = Integer.parseInt(br.readLine());
    }


    /**
     * Lit le contenu du terrain de la map
     * Nombre de lignes variables, correspond au taille Y
     * @param br pour pouvoir le contenu du fichier en maintenant les lignes déjà lues
     * @throws IOException possible d'avoir une erreur lors de la lecture du fichier
     */
    void lireMap(BufferedReader br) throws IOException {
        map = new CaseMap[tailleX][tailleY];
        String ligne;
        for (int i = 0; i < tailleY; i++) {
            ligne = br.readLine();
            int tailleTab = ligne.length();
            char[] tab = ligne.toCharArray();

            if(ligne != null){
                for(int j = 0; j < tailleTab; j++){
                    if(tab[j] == ' '){
                        map[j][i] = new CaseMap(j,i);
                    }
                    else {
                        Sens senstempo = obtenirLeSens(tab[j]);
                        if(senstempo == Sens.Pivotant){
                            map[j][i] = new Rail(j,i, Sens.Horizontal, true );
                        }else{
                            map[j][i] = new Rail(j,i, senstempo, false );
                        }

                    }
                }
            }
        }
    }

    //Méthode lisant les trains se trouvant dans le fichier

    /**
     * Lit les trains se trouvant dans le  fichier
     * Lignes se trouvant après le contenu du terrain
     * @param br pour pouvoir le contenu du fichier en maintenant les lignes déjà lues
     * @throws IOException possible d'avoir une erreur lors de la lecture du fichier
     */
    void lireTrains(BufferedReader br) throws IOException{
        String ligne;
        trains = new ArrayList();
        do{
            ligne = br.readLine();
            if (ligne != null)
            {

                String[] parts = ligne.split(" ");
                int posX = Integer.parseInt(parts[0]);
                int posY = Integer.parseInt(parts[1]);
                int NbWagons = Integer.parseInt(parts[2]);
                String sens = parts[3];
                if(map[posX][posY] != null && map[posX][posY].getClass().getName() == "models.Rail")
                {
                    Train tmp = new Train(posX, posY, NbWagons, obtenirLaDirection(sens));
                    trains.add(tmp);
                    System.out.println("Train crée: Locomotive en " + posX + "," + posY + " et contient " + NbWagons + " wagons. Il se dirige vers: " + sens);
                }
                else {
                    System.err.println("Train ignoré, n'est pas sur les rails !");
                }
            }
        }
        while(ligne != null);
    }

    /**
     * Permet d'obtenir la direction du train selon le caractère du fichier
     * @param sens récupère le caractère correspondant au sens pour en obtenir la direction
     * @return la direction du train
     */
    Direction obtenirLaDirection(String sens) {
        switch(sens){
            case "L":
                return Direction.Left;
            case "R":
                return Direction.Right;

            case "T":
                return Direction.Top;

            case "B":
                return Direction.Bottom;

            default:
                return Direction.Default;
        }
    }

    /**
     * Récupère le caractère du rail correspondant
     * @param c caractère du contenu du fichier
     * @return le sens correspondant au rail
     */
     Sens obtenirLeSens(char c){
        switch (c){
            case '─':
                return Sens.Horizontal ;

            case '│':
                return Sens.Vertical ;

            case '╰':
                return Sens.DescenteDroite ;

            case '╯':
                return Sens.DescenteGauche ;

            case '╭':
                return Sens.MontéeDroite ;

            case '╮':
                return Sens.MontéeGauche ;

            case '┼':
                return Sens.Croisement ;

            default:
                return Sens.Defaut;
        }
    }
}
