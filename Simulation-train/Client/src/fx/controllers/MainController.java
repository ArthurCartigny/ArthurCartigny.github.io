package fx.controllers;

import fx.views.AffichageFx;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import models.Map;
import network.NetworkProtocol;
import network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Controller principal du programme en FX
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class MainController extends Application {

    /**
     * Permet l'envoi de données en client/serveur
     */
     ObjectSocket objectSocket;
    /**
     * Map principale du programme, contient les trains et le terrain
     */
     Map mainPlateau;
    /**
     * Controller FX
     */
     AffichageFx afficheur;
    /**
     * Type de client
     */
     String nom = "Afficheur";

    /**
     * Methode lancée au démarrage du programme
     * Charge AffichageFx
     * @see AffichageFx
     * Connecte au serveur puis affiche le terrain
     * @param stage affiche le programme dans le stage
     * Lance un thread pour écouter et mettre à jour le gridpane
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(AffichageFx.class.getResource("AffichageFx.fxml"));
            Parent root = loader.load();
            afficheur = loader.getController();
            afficheur.setListener(()-> {
                try {

                    objectSocket.write(afficheur.jeSuis);
                    objectSocket.write(afficheur.jeFait);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            connectToServer();
            afficheur.setupMap(mainPlateau);
            afficheur.dessinerTrains();

            stage.setScene(new Scene(root));
            stage.setTitle("Affichage");
            stage.resizableProperty().setValue(false);
            stage.show();

        } catch (Exception e) {
            showError("Erreur de chargement de la fenêtre");
        }

        ReadUpdateMap readUpdateMap = new ReadUpdateMap(objectSocket, this);
        readUpdateMap.start();

        //Stop thread quand la fenêtre se ferme

    }

    /**
     * Methode lançant la connexion à un serveur
     * @throws IOException erreur de communication
     */
    private void connectToServer() throws IOException {
        Socket socket = new Socket("localhost", NetworkProtocol.PORT);
        objectSocket = new ObjectSocket(socket);
        objectSocket.write(nom);
        try {
            mainPlateau = objectSocket.read();
        } catch (ClassNotFoundException e) {
            System.out.println("Problème avec la lecture de classe");
        }
        System.out.println("Connecté au serveur");
    }



    /**
     * Se lance à l'exécution du programme
     * @param args pas utilisé ici
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ouvre une nouvelle fenêtre si une erreur est générée lors du lancement de la fênetre
     * @param s contenu de la fênetre d'erreur
     */
    private void showError(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR, s);
        alert.showAndWait();
    }


    /**
     * Methode appelée lorsque le client reçoit une nouvelle map du serveur
     * @param map nouvelle map du serveur
     */
    public void onNewMap(Map map) {
        afficheur.updateMapOnFxThread(map);
    }
}
