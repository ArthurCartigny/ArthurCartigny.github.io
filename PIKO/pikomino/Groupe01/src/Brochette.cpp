
#include "Brochette.hpp"
#include "EnsembleDes.hpp"
Brochette::Brochette()
{
    //Initialise les bonnes valeurs des pickominos et leur nombre de vers
    int j = 21;
    for (int i = 0; i < tailleBrochette; i++)
    {
        if (j < 25)
        {
            broch[i] = new Pikomino(j,1);
        }
        else if (j < 29)
        {
            broch[i] = new Pikomino(j,2);
        }
        else if (j < 33)
        {
            broch[i] = new Pikomino(j,3);
        }
        else
        {
            broch[i] = new Pikomino(j,4);
        }
        j++;
    }
}
void Brochette::affichageBroch()
{
    cout << "Pickominos restants: ";
    for (int i = 0; i < tailleBrochette; i++)
    {
        if (broch[i] != nullptr && !broch[i]->GetEstRetourne())
            cout << broch[i]->GetValeur() << "[" << broch[i]->GetNbVer() << "] ";
    }
    cout << endl;
}

Pikomino *Brochette::RecupPiko(int somme, int Nbver)
{      
    
    tmp = nullptr;
    for (int i = tailleBrochette - 1; i >= 0; i--)
    {
        if (broch[i] != nullptr && !broch[i]->GetEstRetourne())
        {
            if(Nbver >= 4 )
            {
                tmp = broch[i];
                broch[i] = nullptr;
                cout << "WOW 4 Vers !! " << endl;
                cout << "Vous obtenez le pickomino " << tmp->GetValeur() << "[" << tmp->GetNbVer() << " ver(s)] !" << endl;
                return tmp;   
            }
            if (somme >= broch[i]->GetValeur())
            {
                //On passe par un tmp pour sauvegarder la valeur du pickomino pour pouvoir le mettre à nullptr dans la brochette sans le supprimer
                tmp = broch[i];
                broch[i] = nullptr;
                cout << "Vous obtenez le pickomino " << tmp->GetValeur() << "[" << tmp->GetNbVer() << " ver(s)] !" << endl;
                return tmp;
            }
        }
    }
}

Brochette::~Brochette()
{
    for (int i = 0; i < tailleBrochette; i++)
    {
        broch[i]->~Pikomino();       
    }
    
}

bool Brochette::BrochetteEstVide()
{
    //Compte le nombre de pickominos indisponible dans la brochette
    int compteur = 0;
    for (int i = 0; i < tailleBrochette; i++)
    {
        if (broch[i] != nullptr)
        {
            if (broch[i]->GetEstRetourne())
            {
                //Si le picko est dans la brochette, s'il est retourné il est indisponible, on incrémente le compteur
                compteur++;
            }
        }
        else
        {
            //A chaque fois qu'un picko est nullptr, c'est qu'il est dans une pile, on incrémente
            compteur++;
        }
    }

    //S'il n'y a plus de pickominos disponible dans la brochette, la brochette est vide 
    if (compteur == tailleBrochette)
    {
        return true;
    }
    else
    {
        return false;
    }
}

void Brochette::retournerPikomino()
{
    cout << "On retourne le dernier Pickomino visible" << endl;
    //Retourne le pickomino avec la valeur la plus haute 
    for (int i = tailleBrochette - 1; i >= 0; i--)
    {
        if (broch[i] != nullptr && !broch[i]->GetEstRetourne())
        {
            broch[i]->SetEstRetourne();
            break;
        }
    }
}

bool Brochette::peutPrendreUnPikomino(int somme)
{
    //Vérifie si la somme correspond au minimum à la valeur d'un pickomino de la brochette
    for (int i = 0; i < tailleBrochette; i++)
    {
        if (broch[i] != nullptr && !broch[i]->GetEstRetourne())
        {
            valeurPremierPikominoDispo = broch[i]->GetValeur();
            break;
        }
    }

    if (somme >= valeurPremierPikominoDispo)
    {
        return true;
    }
    else
    {
        return false;
    }
}
