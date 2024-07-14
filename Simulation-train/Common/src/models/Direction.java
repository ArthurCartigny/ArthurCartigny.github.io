package models;

import java.io.Serializable;
/**
 * Enumérateur permettant de mettre et récupérer la direction d'un train
 * @see Train
 * @see PartieDuTrain
 * @author AnthonyM&ArthurC
 * @version 1.0
 */
public enum Direction implements Serializable {
    Top("T"),
    Left("L"),
    Bottom("B"),
    Right("R"),
    Default(" ");

    /**
     * Attribut de l'énumérateur
     */
    private String name;

    /**
     * Constructeur de l'énumérateur
     * @param name Récupère le nom de la direction
     */
    Direction(String name) {
        this.name = name;
    }

    /**
     * Renvoie l'énumérateur en une chaîne de caractère
     * @return convertit l'énumérateur en une chaine de caractère
     */
    public String toString() {
        return name;
    }
}
