package models;

import java.io.Serializable;

/**
 * Enumérateur représentant le caractère d'un rail
 * @see Rail
 *
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public enum Sens implements Serializable {
    Horizontal("─"),
    Vertical("|"),
    DescenteDroite("╰"),
    DescenteGauche("╯"),
    MontéeDroite("╭"),
    MontéeGauche("╮"),
    Croisement("┼"),
    Defaut("#"),
    Pivotant("=");

    //Création de leur attribut
    public String name;

    /**
     * Constructeur de l'énumérateur
     * @param name l'équivalent en string de l'énumérateur
     */
    Sens(String name) {
        this.name = name;
    }

    /**
     * Converti l'énumérateur en string
     * @return la conversion en string de l'énumérateur
     */
    public String toString() {
        return name;
    }
}
