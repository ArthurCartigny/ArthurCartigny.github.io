package fx.controllers;

import models.Map;
import network.ObjectSocket;

import java.io.IOException;

/**
 * Thread qui écoute si le client reçoit une nouvelle map du serveur
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class ReadUpdateMap extends Thread {
    /**
     * Permet la communication
     */
    private final ObjectSocket objectSocket;
    /**
     * Controller associée au thread
     */
    private final MainController controller;

    /**
     * Constructeur de la classe
     * @param objectSocket objectsocket du serveur
     * @param controller controller du programme
     */
    public ReadUpdateMap(ObjectSocket objectSocket, MainController controller){
        this.objectSocket = objectSocket;
        this.controller = controller;
    }

    /**
     * Executée au lancement du thread
     * Ecoute s'il y a une nouvelle map et appelle la méthode d'actualisation quand il y a une nouvelle map
     * @see Thread
     */
    @Override
    public void run() {
        try {
            while (true) {
                Map map = objectSocket.read();
                controller.onNewMap(map);
            }
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Erreur de communication avec le serveur");
        }
    }
}
