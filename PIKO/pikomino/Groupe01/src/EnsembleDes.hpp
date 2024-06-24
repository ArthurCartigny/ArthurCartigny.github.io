#pragma once
#include "De.hpp"
#include "Windows.h"

class EnsembleDes
{
    //Somme des dés bloqués
    int somme;
    //Rempli le tableau de dés avec valeur aléatoires
    void Remplir();

public:
    //Taille de l'ensemble de dés, constant
    const static int taille = 8;
    //Ensemble des 8 dés
    De ensemble[taille];
    EnsembleDes();
    ~EnsembleDes();
    
    //Affiche les dés bloqués et non bloqués
    void AfficherTout();
    //Change la valeur des dés disponibles
    void Relancer();
    //Calcule la somme des dés bloqués et la renvoie dans la variable "somme"
    int SommeDes();
    //Vérifie s'il y a des dés jouables
    bool contientDesJouables();
    //Change le booléen "bloque" en false des 8 dés
    void ResetDes();
    //Permet de vérifier le nombre de dés bloqués pour la méthode "contientDesJouables"
    int nombre_desBloques = 0;
};