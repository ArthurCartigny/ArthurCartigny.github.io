package network;

import java.io.*;
import java.net.Socket;

/**
 * Classe permettant l'envoi et la récéption de données en réseau
 */
public class ObjectSocket implements AutoCloseable {
    /**
     * Envoi de données
     */
    private final ObjectOutputStream objectOutput;
    /**
     * Reception de données
     */
    private final ObjectInputStream objectInput;

    /**
     * Socket réseau utilisé
     */
    private final Socket socket;

    /**
     * Constructeur de la classe
     * @param socket socket réseau utilisé dans la communication réseau
     * @throws IOException erreur d'entrée/sortie
     */
    public ObjectSocket(Socket socket) throws IOException {
        this.socket = socket;
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        this.objectOutput = new ObjectOutputStream(out);
        this.objectInput = new ObjectInputStream(in);
    }

    /**
     * Envoi un objet en réseau
     * @param object Objet sérializable que l'on veut envoyé en réseau
     * @throws IOException erreur d'entrée/sortie
     */
    public void write(Object object) throws IOException {
        objectOutput.reset();
        objectOutput.writeObject(object);
        objectOutput.flush();
    }

    //Prend le type de l'attribut dans lequel il est utilisé pour le casté automatiquement

    /**
     * Lit un objet reçu en réseau
     * @param <T> type de l'objet reçu avec un cast automatique
     * @return cast automatique de l'objet reçu
     * @throws IOException erreur d'entrée/sortie
     * @throws ClassNotFoundException erreur au cas où le type de l'objet reçu n'existe pas dans le programme
     */
    public <T> T read() throws IOException, ClassNotFoundException {
        return (T)objectInput.readObject();
    }

    /**
     * Coupe la communication réseau
     * @throws IOException erreur de communication
     */
    public void close() throws IOException {
        objectOutput.close();
        objectInput.close();
        socket.close();
    }
}
