#pragma once
#include <iostream>
#include "Joueur.hpp"
using namespace std;
//Hérite de Joueur, joueur humain intéragissable
class Humain : public Joueur
{
private:
public:
    Humain();
    ~Humain();
    //Permet de bloquer un dés manuellement, rédéfinition héritage
    int ChoisirUnDe();
};
