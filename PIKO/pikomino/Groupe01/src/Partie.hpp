#pragma once
#include "Joueur.hpp"
#include "Brochette.hpp"
#include "Tour.hpp"
#include "Bot.hpp"
#include "Humain.hpp"
#include "sqlite3.h"
#include <string>
//Classe centrale du programme
class Partie
{
    //Nombre de joueur d'une partie, constant
    const int nbrjoueur = 4;
    //Id de la partie pour pouvoir la réperer dans le SQL
    int idPartie = 0;
    //Déclaration de la brochette pour la partie
    Brochette broche;
    //Création du tableau de joueurs
    Joueur* joueurs[4];
    //Pointeur de pickominos permettant de gérer le transfert de pickomino d'un joueur vers la brochette
    Pikomino *tmp = nullptr;
   
    //Methode faisant rendre un pickomino de la pile du joueur avec le tour en cours dans la brochette
    void RendLePiko(int numJ);
    //Affiche des informations concernant le tour du joueur
    void AfficherTourJoueur(int numJ);
    //Methode permettant d'insérer les informations de la partie dans la base de données
    void InsererDonneesSql(int idPartie, Joueur * joueurs[], Pikomino* broch[]);

public: 

    Partie(int num);
    ~Partie();
    //Méthode de lancement de partie
    void LancerPartie(int id);
    
    
    
};
