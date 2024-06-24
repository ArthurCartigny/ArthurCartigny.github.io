#pragma once
#include <iostream>
#include "EnsembleDes.hpp"
#include "Joueur.hpp"
#include <windows.h>

class Tour
{
    //Variable permettant de continuer le blocage de dés tant que le tour n'est pas fini
    bool joue = true;
    //Variable permettant de lire la valeur du dés à bloquer
    int input;
    //Vérifie si le joueur a bloqué un dé
    bool aBloqueVer = false;

public:
    //Ensemble de dés du tour
    EnsembleDes ensembleDe;

    Tour();
    ~Tour();
    
    //Lance le tour du joueur
    void JouerTour(Joueur &j);
    //Methode indiquant si le joueur à bloquer un ver
    bool GetBloqueVer();
};
