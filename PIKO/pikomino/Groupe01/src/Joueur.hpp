#pragma once
#include "Brochette.hpp"
#include "EnsembleDes.hpp"
//Classe de base des joueurs
class Joueur
{
    static const int tailleBrochette = 16;
    //Taille max du tableau de pickomino d'un joueur
    static const int taillePile = 16;
    //Nombre de vers d'un joueur, score
    int nbVers;
    
    //Pointeur de pickomino permettant de gérer le transfert de pickomino de la pile d'un joueur vers la brochette
    Pikomino *pickominoARemettre;
    
public:
    Joueur();
    ~Joueur();
    //Utile pour remettre le dernier pickomino du joueur
    int nbPikominoJoueur;
    //Tableau de pickomino d'un joueur
    Pikomino *pile[taillePile];
    //Numero du joueur
    int numero = 0;
    //Permet d'obtenir le nombre de vers du joueur
    int GetNbVers();
    //Déclaration de la méthode de base de choix de dés, définie dans "Humain" et "Bot", 0 car méthode non définie
    virtual int ChoisirUnDe() = 0;
    //Méthode permettant de prendre un pickomino dans la brochette et de la mettre dans la pile
    void PrendPicko(Brochette &broch, int somme);
    //Methode de remise d'un pickomino
    Pikomino *RemisePicko();
    //Methode de bloquage d'un dé
    bool BloquerUnDe(EnsembleDes &des, int choix);
    int GetNbpiko();
    
    
};
