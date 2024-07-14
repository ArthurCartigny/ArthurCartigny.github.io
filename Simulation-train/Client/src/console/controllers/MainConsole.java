package console.controllers;

import console.views.AfficheMap;
import fx.controllers.MainController;
import models.Map;
import network.NetworkProtocol;
import network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Controller principal du programme en console
 * @deprecated Il faut mieux utiliser l'affichage en FX
 * @see MainController
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class MainConsole {

    /**
     * Map principale du programme
     * Contient les trains et le terrain
     * @see Map
     */
     Map map;

    /**
     * Lance le programme
     * @param args pas utilisé ici
     */
    public static void main(String[] args) {
        MainConsole main = new MainConsole();
        main.runClient();
    }

    /**
     * Connecte le client au serveur et récupère la map du serveur pour l'afficher
     */
    private void runClient(){
        try {
            Socket socket = new Socket("localhost", NetworkProtocol.PORT);
            try (ObjectSocket objectSocket = new ObjectSocket(socket)) {
                System.out.println("Connecté au serveur !");

                objectSocket.write("Télécommande");
                //Reception de la map du serveur
                map = objectSocket.read();

                System.out.println(map.tailleX + " " + map.tailleY);
                AfficheMap afficheMap = new AfficheMap(map);
                afficheMap.afficherMap();

                while (true) {
                    map = objectSocket.read();
                    afficheMap = new AfficheMap(map);
                    afficheMap.afficherMap();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la communication avec le serveur");
        }
    }
}
