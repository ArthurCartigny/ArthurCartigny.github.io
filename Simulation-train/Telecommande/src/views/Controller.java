package views;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Afficheur Fx liée au fichier FXML "Télécommande.fxml"
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public class Controller {
    /**
     * Action actuelle à envoyer au serveur
     */
    String action;

    String bouton;
    /**
     * Bouton qui modifie l'action en Start
     */
    @FXML
    private Button ButtonStart;
    /**
     * Bouton qui modifie l'action en Stop
     */
    @FXML
    private Button ButtonStop;

    @FXML
    private Button ButtonReset;

    /**
     * Listener des boutons
     */
    private Listener listener;

    /**
     * Permet d'alerter le listener que l'on a appuyé sur le bouton
     * @param listener
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * modifie l'action puis desactive le bouton
     * @param event a l'appui du bouton
     */
    @FXML
    void TrainRoule(Event event) {
        action = "Roule";
        desactiverBouton();
        //Appel du listener
        listener.onClick();

    }

    /**
     * modifie l'action puis desactive le bouton
     * @param event a l'appui du bouton
     * Previens le listener que l'action a été modifiée
     */
    @FXML
    void TrainStop(Event event) {
        action = "Stop";
        desactiverBouton();
        //Appel du listener
        listener.onClick();
    }

    /**
     * modifie l'action puis desactive le bouton
     * @param event a l'appui du bouton
     * Previens le listener que l'action a été modifiée
     */
    @FXML
    void TrainStep(Event event) {
        action = "Step";
        desactiverBouton();
        //Appel du listener
        listener.onClick();
    }

    @FXML
    void ResetMap(Event event) {
        action = "Reset";
        listener.onClick();

    }



    /**
     * Getter pour l'action
     * @return une chaîne de caractère correspondant à l'action
     */
    public String envoiAction() {
        return action;
    }

    /**
     * Appelle une méthode lorsque l'on fait appel au listener
     */
    public interface Listener {
        void onClick();
    }

    /**
     * Desactive le bon bouton en fonction de celui qui est déjà activé
     */
    void desactiverBouton(){
        if(action.equals("Roule")){
            ButtonStart.setDisable(true);
            ButtonStop.setDisable(false);
        }
        else {
            ButtonStart.setDisable(false);
            ButtonStop.setDisable(true);
        }
    }

    /**
     * Modifie l'action en cours de la télécommande
     * @param nouvelleAction action reçue par le serveur
     */
    public void setAction(String nouvelleAction) {
        action = nouvelleAction;
        desactiverBouton();
    }

}
