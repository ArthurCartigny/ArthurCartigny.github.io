#include <iostream>
#include "Partie.hpp"
#include "Requetes.hpp"
using namespace std;

int main(int argc, char *argv[])
{
    srand(time(NULL));
    
    int nbHumain = 0;
    //Conversion de l'argc en un int pour pouvoir y entrer le nombre de joueurs humain lors du lancement du main.exe
    if(argc==2)
    {
        if(*argv[1]>=48 && *argv[1] <=52)
        {
            nbHumain = *(argv[1]) - 48;
        }
    }
    
    //Lance la partie
    Partie p1(nbHumain);
    
    //Changer le paramètre quand création de nouvelle partie pour rentrer correctement dans la db puis recompiler
    int numPartie = 0;  
    p1.LancerPartie(numPartie);


    cout << "---------------------REQUETES SQL-------------------" << endl;
    Requetes r1;
    r1.AfficherScore();
    r1.CompterLesVictoires();
    r1.TauxRetournement();
    return 0;
}

// ./a.exe 0