#pragma once
#include <stdlib.h>
#include <time.h>
#include <iostream>

using namespace std;

class De
{
    int valeur;
    //Variable permettant de voir si un dés est bloqué ou disponible
    bool bloque = false;

public:
    De();
    //Affiche la valeur du dé
    void Afficher();
    //Change la valeur du dé
    void ChangerValeur();
    //Accesseur pour lire la valeur d'un dé
    int getValeur();
    //Renvoi le blocage
    bool estBloque();
    //Mets le booléen "bloque" à vrai 
    void SetBlocage();
    //Mets le booléen bloque à false
    void RetirerBlocage();
};
