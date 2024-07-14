import fileReader.LectureFichier;
import models.Map;
import models.Train;
import network.NetworkProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe qui exécute le programme
 * Fait office de serveur
 * Contient une map et gère les mouvements des trains sur cette map qui est chargée par un fichier
 * @see Map
 * @see LectureFichier
 *
 * Le path du fichier doit être mis en argument lors de l'execution du programme
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Server {
    /**
     * Liste de télécommandes connectées au serveur
     */
    public List<ClientThread> telecommandes;

    /**
     * Map principale du programme, chargée par la lecture du fichier
     * @see LectureFichier
     */
    Map mainPlateau;
    /**
     * Chemin du fichier
     */
    static String path;
    /**
     * Action de la map
     * Est de "Stop" de base pour que les trains n'avancent pas lors du lancement du serveur
     */
    static String ordre = "Stop";
    static boolean tmp ;
    public List<ClientThread> afficheur;

    /**
     * Main du programme serveur
     * @param args doit contenir le chemin du fichier map à charger
     */
    public static void main(String[] args) {
        //Doit contenir en args : "Ressources/map.map"
        path = args[0];
        Server server = new Server();
        server.runServer();

    }

    /**
     * Permet de se connecter au réseau selon les protocoles définis
     * @see NetworkProtocol
     * Charge le fichier map puis le place dans la map du serveur
     * @see LectureFichier
     * @see Map
     * Execute un thread associée aux déplacements des trains puis écoute si des clients se connectent au serveur et execute un thread pour chaque client
     * @see ClientThread
     */
    private void runServer() {
        telecommandes = new ArrayList<>();
        afficheur = new ArrayList<>();
        try {
            ServerSocket serverSocket = new ServerSocket(NetworkProtocol.PORT);

            //Lecture du fichier map
            LectureFichier reader = new LectureFichier(path);
            //Création de la carte grace au fichier mis en paramètres
            mainPlateau = new Map(reader);

            setTrain();
            setThread();

            while (true) {

                System.out.println("En attente de client... ");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepté !");

                //Lecture du client dans un thread séparé
                ClientThread clientThread = new ClientThread(socket,this, mainPlateau);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Le serveur n'a pas pu démarrer");
        }
    }

    /**
     * Thread executant les déplacements des trains sur la map en fonction de l'ordre actuel du serveur
     * @see Thread
     * @see Map
     * @see Train
     */

    private void resetMap(){
        //Lecture du fichier map
        LectureFichier reader = new LectureFichier(path);
        //Création de la carte grace au fichier mis en paramètres
        mainPlateau = new Map(reader);
        setTrain();
        refreshClient();

    }

    public void refreshClient(){
        for(ClientThread clientThread : afficheur){
            clientThread.map = mainPlateau;
        }
    }

    public void setTrain(){
        for(Train train : mainPlateau.trains){
            train.demarrerLeTrain(mainPlateau);
            mainPlateau.updateMap();
        }
    }

    private void setThread(){
        new Thread(() -> {

            while(true) {

                switch (ordre){

                    case "Roule" :
                        tmp =true;


                        deplacementDesTrain();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;

                    case "Stop" :
                        System.out.print("");

                        tmp = false;

                        break;

                    case "Step":

                        deplacementDesTrain();
                        ordre = "Stop";

                        break;

                    case "Reset":

                        System.out.println("reset");

                        resetMap();

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(tmp == true)
                        {
                            ordre = "Roule";
                        }else{
                            ordre ="Stop";
                        }
                        break;
                }


            }
        }).start();
    }

    /**
     * Methode qui fait appel à chaque train de la map pour le déplacer sur les rails
     * @see Train
     */
     void deplacementDesTrain() {
        for(Train train : mainPlateau.trains){
            if(train.Avance == true)
            {
                train.deplacementTrain();
            }
            mainPlateau.updateMap();
        }
    }

    /**
     * Methode de broadcast aux télécommandes
     * @param object objet à envoyer aux télécommandes
     */
    public synchronized void broadcast(Object object) {
        broadcastBut(object, null);
    }

    /**
     * Methode de broadcast exceptée pour le client thread qui a envoyé l'ordre
     * @param object objet à envoyer
     * @param allButClientThread client qui envoie le message
     */
    public void broadcastBut(Object object, ClientThread allButClientThread) {
        synchronized (telecommandes) {
            for (ClientThread clientThread : telecommandes) {
                if (clientThread.equals(allButClientThread))
                    continue;
                try {
                    clientThread.send(object);
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * Retire le client de la liste de télécommande à la deconnexion
     * @param clientThread client qui doit être supprimé de la liste
     */
    public void clientTerminated(ClientThread clientThread) {
        synchronized (telecommandes) {
            this.telecommandes.remove(clientThread);
        }
    }
}
