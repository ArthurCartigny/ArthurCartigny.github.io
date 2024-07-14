package controller;

import fileReader.LectureFichier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Map;
import network.NetworkProtocol;
import network.ObjectSocket;
import views.Controller;

import java.io.IOException;
import java.net.Socket;

/**
 * Controller de la télécommande
 * Considéré comme un client qui envoie un ordre au serveur
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class MainTelecommande extends Application {
    /**
     * Permet la communication réseau
     */
    ObjectSocket objectSocket;
    /**
     * Type de client
     */
    String nom = "Télécommande";

    /**
     * Executée au lancement du programme
     * Charge les paramètres FX de la télécommande
     * Envoie un ordre au serveur lorsqu'un des boutons est pressé
     * Un thread est lancé pour écouter si un nouvel ordre devrait arriver
     * @param primaryStage fenêtre dans laquelle la télécommande est affichée
     * @throws Exception au cas où le programme n'arrive pas à charger la fenêtre
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Controller.class.getResource("Télécommande.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        connecteAuServeur(controller);
        controller.setListener(() -> {
            try {
                //Envoi l'état du string Action du controller au serveur lorsque le listener est appelé

                    objectSocket.write(controller.envoiAction());


                //Il faudrait à ce moment que la télécommande envoie l'ordre à toutes les autres télécommandes
            } catch (IOException e) {
                System.err.println("Erreur dans la communication");
            }
        });

        new Thread(()->{
            try {
                while(true) {
                    controller.setAction(objectSocket.read());
                }
            } catch (IOException | ClassNotFoundException e) {
                //e.printStackTrace();
            }
        }).start();

        primaryStage.setTitle("Télécommande");
        primaryStage.setScene(new Scene(root, 372, 90));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();


    }

    /**
     * Etablit la connection avec le serveur
     * @throws IOException s'il y a une erreur de communication
     * @throws ClassNotFoundException si la classe String n'est pas trouvé dans le programme
     */
    public void connecteAuServeur(Controller controller) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", NetworkProtocol.PORT);
        objectSocket = new ObjectSocket(socket);
        objectSocket.write(nom);
        controller.setAction(objectSocket.read());
    }

    /**
     * Main du programme
     * @param args utilisée pour lancer le programme
     */
    public static void main(String[] args) {
        launch(args);
    }
}