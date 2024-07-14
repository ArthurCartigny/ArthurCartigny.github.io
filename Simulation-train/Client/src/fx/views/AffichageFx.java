package fx.views;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.*;

import java.net.http.WebSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de l'affichage en fx
 * Liée au fichier fmxl "AffichageFx.fxml"
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class AffichageFx {
    /**
     * Map qui doit être affichée
     */
 Map map;
    /**
     * Nombre de trains dans la map
     */
 int nombreDeTrains;
    /**
     * Nombre le plus élevé de wagons afin de créer un tableau à deux dimensions
     */
 int nombreDeWagonsMax = 0;
    /**
     * Liste de colorpicker afin de récupérer le contenu du bon colorpicker pour le bon cercle/train
     * @see ColorPicker
     */
 List<ColorPicker> choixCouleur;
    /**
     * Tableau de cercle à deux dimension : une pour le nombre de train et une pour le nombre de wagon le plus élévé
     * Associe un cercle à un train
     */
 Circle[][] trainsGraphiques;

    /**
     * A gauche de l'affichage
     * Contient les colorpicker et un label associée à chaque train
     */
    @FXML
    AnchorPane colorMenu;

    /**
     * Contient le terrain
     */
    @FXML
    GridPane mainGrid;

    public Listener listener;
    public int jeSuis;
    public String jeFait;
    /**
     * Récupère le contenu de la map pour créer les trains, créer les colorpickers et mettre les images correspondants aux rails
     * @param mainMap map à afficher
     */
    public void setupMap(Map mainMap) {
        map = mainMap;
        setupTrains();
        trainsGraphiques = new Circle[nombreDeTrains][nombreDeWagonsMax+1];
        setupMenuCouleur();
        mainGrid.getChildren().clear();
        for (int y = 0; y < mainMap.tailleY; y++) {
            mainGrid.getRowConstraints().add(new RowConstraints(50));
            for (int x = 0; x < mainMap.tailleX; x++) {
                if (mainMap.map[x][y] != null) {
                    dessinerChemin(mainMap.map[x][y], y, x);

                }
            }
        }
        for (int x = 0; x < mainMap.tailleX; x++) {
            mainGrid.getColumnConstraints().add(new ColumnConstraints(50));
        }

    }

    /**
     * Charge la bonne image dans la bonne case du gridpane
     * @param casemap récupère le contenu de la case pour y placer la bonne image
     * @param x coordonnée en x de la case du gridpane
     * @param y coordonnée en y de la case du gridpane
     */
    void dessinerChemin(CaseMap casemap, int x, int y) {
        //condition pour afficher le sens du rail
        if (casemap.getClass().getName().equals("models.Rail")) {
            Rail casetmp = (Rail) casemap;
                switch (casetmp.getSens()) {
                    case Croisement:
                        mainGrid.add(new ImageView(new Image("Ressources/Crossed.JPG")), y, x);
                        break;
                    case DescenteDroite:
                        mainGrid.add(new ImageView(new Image("Ressources/bottomLeft.png")), y, x);
                        break;
                    case DescenteGauche:
                        mainGrid.add(new ImageView(new Image("Ressources/bottomRight.png")), y, x);
                        break;
                    case MontéeDroite:
                        mainGrid.add(new ImageView(new Image("Ressources/topLeft.JPG")), y, x);
                        break;
                    case MontéeGauche:
                        mainGrid.add(new ImageView(new Image("Ressources/topRight.png")), y, x);
                        break;
                    case Vertical:
                        mainGrid.add(new ImageView(new Image("Ressources/vertest.JPG")), y, x);
                        break;
                    case Horizontal:
                        mainGrid.add(new ImageView(new Image("Ressources/hortest.JPG")), y, x);
                        break;

                    default:
                        //mainGrid.add(new ImageView(new Image("Ressources/fond.png")), y, x);
                        break;
                }
            }
        }

    /**
     * Charge les trains de la map et recupère le plus grand nombre de wagon
     */
    public void setupTrains(){
        nombreDeTrains = map.trains.size();

        for(Train train : map.trains){
            if(train.wagons.length > nombreDeWagonsMax){
                nombreDeWagonsMax = train.wagons.length;
            }
        }
        //dessinerTrains();
    }

    /**
     * Crée les colorpicker et les labels pour chaque train dans l'anchorpane
     */
    void setupMenuCouleur(){
        final int OFFSETX = 10;
        final int OFFSETY =  70;
        final int OFFSETLABELANDCP = 17;
        final int OFFSETBOUTONBLANC = 30;
        int compteurDeTrains = 1;
        choixCouleur = new ArrayList<>();

        for(Train train : map.trains){

            Label lb = new Label("Train n°" + compteurDeTrains);
            lb.setLayoutX(OFFSETX);
            lb.setLayoutY(compteurDeTrains*(OFFSETY));
            colorMenu.getChildren().add(lb);

            ColorPicker cp = new ColorPicker();
            cp.setLayoutX(OFFSETX);
            cp.setLayoutY(OFFSETLABELANDCP +compteurDeTrains*(OFFSETY));
            choixCouleur.add(cp);
            cp.setValue(Color.DARKGRAY);
            colorMenu.getChildren().add(cp);

            Button stop = new Button();
            stop.setLayoutX(OFFSETX);
            stop.setLayoutY(OFFSETBOUTONBLANC +OFFSETLABELANDCP+ compteurDeTrains*(OFFSETY));
            stop.setText("Mettre en pause");
            stop.setPrefWidth(140);
            final int numtrain = compteurDeTrains-1;

            colorMenu.getChildren().add(stop);

            Button marche = new Button();
            marche.setLayoutX(OFFSETX);
            marche.setLayoutY(OFFSETBOUTONBLANC +OFFSETLABELANDCP+ compteurDeTrains*(OFFSETY));
            marche.setText("Mettre en marche");
            marche.setPrefWidth(140);
            marche.setVisible(false);
            colorMenu.getChildren().add(marche);

            marche.setOnAction((ActionEvent actionEvent)->{


                jeSuis = numtrain;
                jeFait= "Avance";
                listener.Onclick();
                stop.setVisible(true);
                marche.setVisible(false);
            });

            stop.setOnAction((ActionEvent actionEvent)->{


                jeSuis = numtrain;
                jeFait = "Stop";
                listener.Onclick();
                stop.setVisible(false);
                marche.setVisible(true);
            });

            compteurDeTrains++;
        }
    }





    /**
     * Dessine les cercle associés aux trains sur les bonnes cases du gridpane
     */
    public void dessinerTrains(){
        int compteurTrain = 0;
        int compteurPartieDuTrain;

        for(Train train : map.trains) {
            //Pas super propre, peut être jouer avec un onChange
            Color tmp = choixCouleur.get(compteurTrain).getValue();

            trainsGraphiques[compteurTrain][0] = new Circle(15, tmp);
            mainGrid.add(trainsGraphiques[compteurTrain][0], train.locomotive.positionX, train.locomotive.positionY);
            compteurPartieDuTrain = 1;
            for(Wagon wagon : train.wagons){
                trainsGraphiques[compteurTrain][compteurPartieDuTrain] = new Circle(15, tmp);
                mainGrid.add(trainsGraphiques[compteurTrain][compteurPartieDuTrain], wagon.positionX, wagon.positionY);
                compteurPartieDuTrain++;
            }
            compteurTrain++;
        }
    }

    /**
     * Supprime les cercles sur le gridpane
     */
    public void removeTrains(){
        int compteurTrain = 0;
        for(Train train : map.trains) {
            mainGrid.getChildren().remove(trainsGraphiques[compteurTrain][0]);
            int compteurPartieDuTrain = 1;
            for(Wagon wagon : train.wagons){
                mainGrid.getChildren().remove(trainsGraphiques[compteurTrain][compteurPartieDuTrain]);
                compteurPartieDuTrain++;
            }
            compteurTrain++;
        }
    }

    /**
     * Mets à jour la map associée à l'affichage
     * @param map nouvelle map reçu par le controller
     */
    public void updateMap(Map map){
        this.map =  map;
    }

    /**
     * Mets à jour l'affichage du programme en supprimant, mettant à jour la carte et redessinant les cercles sur le gridpane
     * @param map nouvelle map pour mettre à jour l'affichage, reçu par le controller
     */
    public void updateMapOnFxThread(Map map) {
        Platform.runLater(()->{
            this.removeTrains();
            this.updateMap(map);
            this.dessinerTrains();
        });
    }

    public interface Listener {

        public void Onclick();


    }

    public void setListener(Listener listener){
        this.listener = listener;
    }
}
