#pragma once
#include "Pikomino.hpp"
#include <iostream>

using namespace std;

//Gère le tableau commun des pickominos
class Brochette
{
    //Taille du tableau, constant
    static const int tailleBrochette = 16;
    //Permet de faire déplacer un pickomino vers un autre tableau joueur
    Pikomino *tmp = nullptr;
    //Utilisé pour mettre la bonne valeur aux pickos
    int valeurPremierPikominoDispo = 21;

public:
    //Methode pour prendre un pickomino de la brochette et la déplacer vers un joueur
    Pikomino *RecupPiko(int somme, int ver);
    //Methode pour afficher la brochette
    void affichageBroch();
    //Methode permettant de savoir si la brochette est vide
    bool BrochetteEstVide();
    //Methode pour retourner le dernier pickomino disponible
    void retournerPikomino();
    //Vérifie si le joueur peut prendre un pickomino de la brochette
    bool peutPrendreUnPikomino(int somme);
    //Tableau principal contenant les pickos
    Pikomino *broch[tailleBrochette];
    Brochette();
    ~Brochette();
};
