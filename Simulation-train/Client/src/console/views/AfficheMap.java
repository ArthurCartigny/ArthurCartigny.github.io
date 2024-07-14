package console.views;

import fx.views.AffichageFx;
import models.CaseMap;
import models.Map;
import models.Rail;

import java.io.Serializable;

import static models.Sens.*;

/**
 * Afficheur de la map en console
 * @deprecated il vaut mieux afficher en fx
 * @see AffichageFx
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class AfficheMap implements Serializable {

    /**
     * Map qui doit être affiché
     */
    Map mainMap;

    /**
     * Constructeur de la classe
     * @param map reçoit la map à afficher
     */
    public AfficheMap(Map map){
        this.mainMap = map;
    }

    /**
     * Dessine la map
     * Parcourt le tableau de la map pour dessiner en fonction du caractère de la case
     */
    public void afficherMap() {
        for (int y = 0; y < mainMap.tailleY-1; y++) {
            for (int x = 0; x < mainMap.tailleX; x++) {
                //Affiche la valeur de la case
                //System.out.print(tableau.map[x][y].getvaleur());
                if(mainMap.map[x][y] != null)
                dessinerChemin(mainMap.map[x][y]);
            }
            //Passe la ligne
            System.out.println();
        }
    }

    /**
     * Dessine un caractère selon le contenu d'une case
     * @param casemap récupère son contenu pour l'afficher
     * Si le casemap est un rail, on dessine le bon rail selon le sens, sinon on fait un espace
     * @see Rail
     */
    void dessinerChemin(CaseMap casemap){
        if(casemap.getClass().getName() == "models.Rail"){
            Rail casetmp = (Rail) casemap;
            //condition pour afficher le sens du rail

            if(casetmp.train != null){
                System.out.print("#");
            }
            else{
                switch (casetmp.getSens()) {
                    case Croisement:
                        System.out.print(Croisement);
                        break;
                    case DescenteDroite:
                        System.out.print(DescenteDroite);
                        break;
                    case DescenteGauche:
                        System.out.print(DescenteGauche);
                        break;
                    case MontéeDroite:
                        System.out.print(MontéeDroite);
                        break;
                    case MontéeGauche:
                        System.out.print(MontéeGauche);
                        break;
                    case Vertical:
                        System.out.print(Vertical);
                        break;
                    case Horizontal:
                        System.out.print(Horizontal);
                        break;
                    default:
                        System.out.print(Defaut);
                        break;
                }
            }
        }
        else{
            System.out.print(" ");
        }
    }
}
