#include "De.hpp"

De::De()
{
}

void De::ChangerValeur()
{
    
    valeur = rand() % 6 + 1; //Génère un nombre entre 1 et 6
    
}

void De::Afficher()
{
    if (valeur == 6)
    {
        cout << " V ";
    }
    else
    {
        cout << " " << valeur << " ";
    }
}

int De::getValeur()
{
    return this->valeur;
}

bool De::estBloque()
{
    return this->bloque;
}

void De::SetBlocage()
{
    this->bloque = true;
}

void De::RetirerBlocage()
{
    this->bloque = false;
}