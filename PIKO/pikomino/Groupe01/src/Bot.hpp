#pragma once
#include <iostream>
#include "Joueur.hpp"
using namespace std;

//Hérite de joueur: Ordinateur
class Bot : public Joueur
{
private:
public:
    Bot();
    ~Bot();
    //Charge automatiquement un nombre random à bloquer, rédéfinition héritage
    int ChoisirUnDe();
};
