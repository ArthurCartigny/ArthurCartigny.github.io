#include "EnsembleDes.hpp"

EnsembleDes::EnsembleDes()
{
    Remplir();
}

EnsembleDes::~EnsembleDes()
{

}

void EnsembleDes::Remplir()
{
    for (int i = 0; i < taille; i++)
    {
        ensemble[i].ChangerValeur();
    }
}

void EnsembleDes::AfficherTout()
{
    SommeDes();
    cout << "******************************" << endl;
    cout << "Dés bloqués: ";
    for (int i = 0; i < taille; i++)
    {
        if (ensemble[i].estBloque())
            ensemble[i].Afficher();
    }
    cout << " (somme: " << somme << ")" << endl;

    cout << "Dés non bloqués: ";
    for (int i = 0; i < taille; i++)
    {
        if (!ensemble[i].estBloque())
            ensemble[i].Afficher();
    }
    cout << endl;
    //Sleep(800);
}

void EnsembleDes::Relancer()
{

    
    for (int i = 0; i < taille; i++)
    {
        if (!ensemble[i].estBloque())
            ensemble[i].ChangerValeur();
    }
}

int EnsembleDes::SommeDes()
{
    somme = 0;
    int nbver = 0;
    for (int i = 0; i < taille; i++)
    {
        if (ensemble[i].estBloque())
        {
            //Un ver correspond à un 6 sur les dés mais sa valeur vaut 5 dans la somme
            if (ensemble[i].getValeur() == 6)
            {
                somme += 5;
                nbver++;
            }
            else
            {
                somme += ensemble[i].getValeur();
            }
        }
    }
    return somme;
    return nbver;
}

bool EnsembleDes::contientDesJouables()
{
    for (int i = 0; i < taille; i++)
    {
        if (!ensemble[i].estBloque())
        {
            bool estDejaBloque = false;
            for (int j = 0; j < taille; j++)
            {
                if (ensemble[j].estBloque())
                {
                    //Comparaison pour voir si la valeur du dés a déjà été bloqué 
                    if (ensemble[j].getValeur() == ensemble[i].getValeur())
                    {
                        estDejaBloque = true;
                        break;
                    }
                }
            }
            //Si un dés n'a pas été bloqué, il en reste un disponible
            if (!estDejaBloque)
                return true;
        }
    }
    //S'il n'y a pas de dés disponible, il n'y a plus de dés jouables
    cout << "Vous ne pouvez plus jouer..." << endl;
    return false;
}

void EnsembleDes::ResetDes()
{
    for (int i = 0; i < taille; i++)
    {
        ensemble[i].RetirerBlocage();
    }
}
