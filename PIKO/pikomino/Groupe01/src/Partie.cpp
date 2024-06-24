#include "Partie.hpp"
#include "Joueur.hpp"
Partie::Partie(int num)
{
    //Initialise les joueurs selon le nombre d'humain
    if (num <= 4)
    {
        switch (num)
        {
        case 0:
            for (int i = 0; i < 4; i++)
            {
                joueurs[i] = new Bot;
                joueurs[i]->numero = i + 1;
            }

            break;

        case 1:
            joueurs[0] = new Humain;
            joueurs[0]->numero = 1;

            for (int i = 1; i < 4; i++)
            {
                joueurs[i] = new Bot;
                joueurs[i]->numero = i + 1;
            }

            break;

        case 2:
            for (int i = 0; i < 2; i++)
            {
                joueurs[i] = new Humain;
                joueurs[i]->numero = i + 1;
            }

            for (int i = 2; i < 4; i++)
            {
                joueurs[i] = new Bot;
                joueurs[i]->numero = i + 1;
            }

            break;

        case 3:
            for (int i = 0; i < 3; i++)
            {
                joueurs[i] = new Humain;
                joueurs[i]->numero = i + 1;
            }
            joueurs[3] = new Bot;
            joueurs[3]->numero = 4;

            break;

        case 4:
            for (int i = 0; i < 4; i++)
            {
                joueurs[i] = new Humain;
                joueurs[i]->numero = i + 1;
            }
            break;

        default:
            break;
        }
    }
    else
    {
        for (int i = 0; i < 4; i++)
        {
            joueurs[i] = new Bot;
        }
    }
}

Partie::~Partie()
{
}

void Partie::LancerPartie(int idPartie)
{
    int id = idPartie;
    int NumeroJoueurAJouer = 1;
    do
    {
        Tour tour;
        if (NumeroJoueurAJouer > 4)
            NumeroJoueurAJouer = 1;

        cout << "###################################" << endl;
        broche.affichageBroch();
        AfficherTourJoueur(NumeroJoueurAJouer);
        //Permet de mieux se retrouver lors de lecture de données dans le tableau "joueurs"
        int joueurTour = NumeroJoueurAJouer - 1;
        Sleep(800);
        tour.JouerTour(*joueurs[joueurTour]);

        if (tour.GetBloqueVer())
        {
            if (broche.peutPrendreUnPikomino(tour.ensembleDe.SommeDes()))
            {
                joueurs[joueurTour]->PrendPicko(broche, tour.ensembleDe.SommeDes());
            }
            else
            {
                cout << "Vous n'avez pas obtenu assez de points !" << endl;
                RendLePiko(joueurTour);
                broche.retournerPikomino();
            }
        }
        else
        {
            cout << "Vous n'avez pas bloqué de vers !" << endl;
            RendLePiko(joueurTour);
            broche.retournerPikomino();
        }
        NumeroJoueurAJouer++;
        Sleep(800);
    } while (!broche.BrochetteEstVide());

    cout << "--- C'est fini ! ---" << endl;
    cout << "Joueur 1: " << joueurs[0]->GetNbVers() << " ver(s)" << endl;
    cout << "Joueur 2: " << joueurs[1]->GetNbVers() << " ver(s)" << endl;
    cout << "Joueur 3: " << joueurs[2]->GetNbVers() << " ver(s)" << endl;
    cout << "Joueur 4: " << joueurs[3]->GetNbVers() << " ver(s)" << endl;

    InsererDonneesSql(id, joueurs, broche.broch);
}

void Partie::AfficherTourJoueur(int numJ)
{
    switch (numJ)
    {
    case 1:

        cout << "> Joueur 1: " << joueurs[0]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 2:" << joueurs[1]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 3:" << joueurs[2]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 4:" << joueurs[3]->GetNbVers() << " ver(s)" << endl;
        break;
    case 2:

        cout << "Joueur 1: " << joueurs[0]->GetNbVers() << " ver(s)" << endl;
        cout << "> Joueur 2:" << joueurs[1]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 3:" << joueurs[2]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 4:" << joueurs[3]->GetNbVers() << " ver(s)" << endl;
        break;
    case 3:

        cout << "Joueur 1: " << joueurs[0]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 2:" << joueurs[1]->GetNbVers() << " ver(s)" << endl;
        cout << "> Joueur 3:" << joueurs[2]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 4:" << joueurs[3]->GetNbVers() << " ver(s)" << endl;
        break;
    case 4:

        cout << "Joueur 1: " << joueurs[0]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 2:" << joueurs[1]->GetNbVers() << " ver(s)" << endl;
        cout << "Joueur 3:" << joueurs[2]->GetNbVers() << " ver(s)" << endl;
        cout << "> Joueur 4:" << joueurs[3]->GetNbVers() << " ver(s)" << endl;
        break;
    }
}

void Partie::RendLePiko(int numJ)
{
     int nbPiko= joueurs[numJ]->GetNbpiko();
    tmp = joueurs[numJ]->RemisePicko();
   
    //Condition dans le cas où le joueur n'a pas encore de pickomino
    if (tmp != nullptr)
    {
        cout << "Vous rendez le pickomino " << tmp->GetValeur() << "(" << tmp->GetNbVer() << " ver(s))!" << endl;
        if(numJ == 3)
        {
            int nbPiko1= joueurs[0]->GetNbpiko();
            joueurs[0]->pile[nbPiko1 +1 ] = tmp;

        }else
        {
            joueurs[numJ+1]->pile[nbPiko +1 ] = tmp;

        }
    
    }
}

void Partie::InsererDonneesSql(int idPartie, Joueur * joueurs[], Pikomino* broch[])
{
    sqlite3 *db;
    sqlite3_stmt *stmt;

    if (sqlite3_open("parties.db", &db) == SQLITE_OK)
    {
        string sqlstatement = "INSERT INTO Partie values (" + to_string(idPartie) + "," + "strftime('%Y-%m-%d %H-%M-%S','now')" + ");";
        //cout << sqlstatement << endl;

        sqlite3_prepare(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
        sqlite3_step(stmt);                                         //Executing the statement

        for (size_t i = 0; i < nbrjoueur; i++)
        {
            string sqlstatement = "INSERT INTO Joueur values (" + to_string(i + 1) + "," + to_string(joueurs[i]->GetNbVers()) + "," + to_string(idPartie) + ");";
            //cout << sqlstatement << endl;
            sqlite3_prepare(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
            sqlite3_step(stmt);                                         //Executing the statement
        }

        for (size_t i = 0; i < 16; i++)
        {
            int pris;
            if (broch[i] == nullptr)
            {
                pris = 1;
            }
            else
            {
                pris = 0;
            }

            string sqlstatement = "INSERT INTO Pickominos values (" + to_string(i + 21) + "," + to_string(pris) + "," + to_string(idPartie) + ");";
            //cout << sqlstatement << endl;
            sqlite3_prepare(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
            sqlite3_step(stmt);
        }
    }
    else
    {
        cout << "Failed to open db" << endl;
    }
    sqlite3_finalize(stmt);
    sqlite3_close(db);
}

