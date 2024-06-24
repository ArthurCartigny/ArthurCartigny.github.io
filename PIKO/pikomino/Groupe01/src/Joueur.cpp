#include "Joueur.hpp"
#include "De.hpp"

Joueur::Joueur()
{
    for (int i = 0; i < taillePile; i++)
    {
        pile[i] = nullptr;
    }
}

Joueur::~Joueur()
{
}

int Joueur::GetNbVers()
{
    nbVers = 0;
    for (int i = 0; i < taillePile; i++)
    {
        if (pile[i] != nullptr)
        {
            nbVers += pile[i]->GetNbVer();
            
        }
    }
    return nbVers;
}

void Joueur::PrendPicko(Brochette &broch, int somme)
{
        int ver = GetNbVers();
        pile[nbPikominoJoueur] = broch.RecupPiko(somme, ver);
         nbPikominoJoueur++;
    
}
int Joueur::GetNbpiko()
{
    return nbPikominoJoueur;
}
Pikomino *Joueur::RemisePicko()
{
    pickominoARemettre = nullptr;
    if (pile[nbPikominoJoueur - 1] != nullptr && nbPikominoJoueur > 0)
    {
        pickominoARemettre = pile[nbPikominoJoueur - 1];
        pile[nbPikominoJoueur - 1] = nullptr;
        nbPikominoJoueur--;
    }
    return pickominoARemettre;
   
}

bool Joueur::BloquerUnDe(EnsembleDes &des, int choix)
{
    bool estDansDesNonBloques = false;
    bool estDansDesBloques = false;

    for (int i = 0; i < des.taille; i++)
    {
        if (!des.ensemble[i].estBloque())
        {
            if (choix == des.ensemble[i].getValeur())
            {
                estDansDesNonBloques = true;
            }
        }
    }
    
    for (int i = 0; i < des.taille; i++)
    {
        if (des.ensemble[i].estBloque())
        {
            if (des.ensemble[i].getValeur() == choix)
            {
                estDansDesBloques = true;
                break;
            }
        }
    }
   
    if (estDansDesNonBloques && !estDansDesBloques)
    {
        for (int i = 0; i < des.taille; i++)
        {
            if (des.ensemble[i].getValeur() == choix)
            {
                des.ensemble[i].SetBlocage();
                des.nombre_desBloques++;
            }
        }
        return true;
        
    }
    else
    {
        cout << "Valeur invalide\n";
        return false;
    }

   
}

