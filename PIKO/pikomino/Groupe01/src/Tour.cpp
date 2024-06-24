#include "Tour.hpp"

Tour::Tour()
{
}

Tour::~Tour()
{
}

void Tour::JouerTour(Joueur &joueurAJouer)
{
    ensembleDe.ResetDes();
    do
    {
        joue = false;
        ensembleDe.Relancer();
        ensembleDe.AfficherTout();

        if (ensembleDe.contientDesJouables())
        {
            int choixDuDe;
            cout << "Que voulez-vous bloquer ? (0 pour arrêter)" << endl;
            do
            {
                choixDuDe = joueurAJouer.ChoisirUnDe();
            } while (!joueurAJouer.BloquerUnDe(ensembleDe, choixDuDe) || choixDuDe == 0);
            joue = true;
        }
    } while (joue);
    Sleep(800);

    for (int i = 0; i < 8; i++)
    {
        if (ensembleDe.ensemble[i].getValeur() == 6 && ensembleDe.ensemble[i].estBloque())
        {
            aBloqueVer = true;
        }
    }
}

bool Tour::GetBloqueVer()
{
    return aBloqueVer;
}
