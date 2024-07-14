import models.Map;
import network.ObjectSocket;

import java.io.IOException;
import java.net.Socket;

/**
 * Thread qui maintient la connectivité avec chaque client et permettant d'en ajouter un autre au serveur
 * @see Thread
 * Peut être soit un afficheur ou soit une télécommande
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class ClientThread extends Thread{

    /**
     * Socket réseau du serveur
     */
    final Socket socket;
    /**
     * Map associée au serveur
     */
    Map map;

    /**
     * Permet la communication réseau
     */
    private ObjectSocket objectSocket;
    /**
     * Serveur surlequel est connecté le clientthread
     */
    final Server server;

    /**
     * Constructeur de la classe
     * @param socket socket réseau du serveur
     * @param server serveur lié au clientthread
     * @param map map du serveur
     */
    public ClientThread(Socket socket, Server server, Map map) {
        this.socket = socket;
        this.server = server;
        this.map = map;
    }

    /**
     * Exécutée au lancement du thread
     * Lance la connectivité client/serveur grâce à un objectsocket
     * @see ObjectSocket
     * Récupère un string du client et lance une boucle en fonction de si le client est un affichage ou une télécommande
     * Si affichage : renvoie une map toute les 0,5s
     * Si télécommande : écoute s'il y a un nouvel ordre
     */
    @Override
    public void run() {
        try(ObjectSocket objectSocketLocal = new ObjectSocket(socket))
        {
        objectSocket = objectSocketLocal;

            String nom = objectSocket.read();

            switch(nom){
                case "Afficheur" :

                    System.out.println("Nouvel afficheur");
                    synchronized (server.afficheur) {
                        server.afficheur.add(this);
                    }
                    objectSocket.write(map);
                    Thread ecoute = new Thread(()->{

                        while (true){
                            int jeSuis;
                            String jeFais;

                            try {

                                jeSuis= objectSocket.read();
                                jeFais = objectSocket.read();
                                System.out.println(jeFais);
                                System.out.println(jeSuis);

                                switch (jeFais) {

                                    case "Stop":
                                        map.trains.get(jeSuis).Avance = false;
                                        System.out.println("Pause");

                                        break;

                                    case "Avance":
                                        map.trains.get(jeSuis).Avance = true;
                                        System.out.println("Avance");

                                        break;
                                }


                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }

                    });
                    ecoute.start();
                    while (true) {
                        objectSocket.write(map);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.err.println("Erreur avec le thread !");
                        }
                    }

                case "Télécommande":
                    System.out.println("Nouvelle télécommande");
                    synchronized (server.telecommandes) {
                        server.telecommandes.add(this);
                    }
                    objectSocket.write(Server.ordre);
                    while (true){
                        Server.ordre = objectSocket.read();
                        //System.out.println(Server.ordre);
                        server.broadcastBut(server.ordre, this);
                    }


        }


        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur de communication avec le client");
        }
        server.clientTerminated(this);
    }

    /**
     * Methode pour envoyer un objet aux autres clients
     * @param object objet à envoyer
     * @throws IOException au cas où il y a une erreur de communication
     */
    public void send(Object object) throws IOException {
        objectSocket.write(object);
    }
}
