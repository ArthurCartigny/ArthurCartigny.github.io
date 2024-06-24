#include "Requetes.hpp"
Requetes::Requetes()
{
}
Requetes::~Requetes()
{
}

void Requetes::AfficherScore()
{
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int i = 0;
    //Prépare un tableau pour lire sauvegardé les éléments pour pouvoir les afficher (1000 pour être large niveau taille du tableau)
    int score[1000];

    if (sqlite3_open("parties.db", &db) == SQLITE_OK)
    {
        string sqlstatement = "SELECT id, nbVers, idPartie, datePartie FROM Joueur j INNER JOIN Partie p ON j.idPartie = p.numPartie ORDER BY nbVers;";
        sqlite3_prepare_v2(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
        sqlite3_bind_int(stmt, 0, 0);

        int a;
        while (a != 101)
        {
            a = sqlite3_step(stmt);
            //4 colonnes dans la requête donc, 4 informations à lire
            score[i] = sqlite3_column_int(stmt, 0);
            score[i + 1] = sqlite3_column_int(stmt, 1);
            score[i + 2] = sqlite3_column_int(stmt, 2);
            score[i + 3] = sqlite3_column_int(stmt, 3);
            i += 4;
        };
    }
    else
    {
        cout << "Failed to open db" << endl;
    }
    sqlite3_finalize(stmt);
    sqlite3_close(db);

    cout << "Scores: " << endl; 
    for (size_t j = 0; j < i - 4; j += 4)
    {
        cout << "Joueur: " << score[j] << ", Score: " << score[j + 1] << ", Partie: " << score[j + 2] << ", Date: " << score[j + 3] << endl;
    }
}

void Requetes::CompterLesVictoires()
{
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int i = 0;
    //Prépare un tableau pour lire sauvegardé les éléments pour pouvoir les afficher (1000 pour être large niveau taille du tableau)
    int victoires[1000];

    if (sqlite3_open("parties.db", &db) == SQLITE_OK)
    {
        string sqlstatement = "SELECT id, COUNT(id) as Nombre_Victoires FROM (SELECT id, MAX(nbVers) FROM Joueur GROUP BY idPartie) GROUP BY id;";
        sqlite3_prepare_v2(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
        sqlite3_bind_int(stmt, 0, 0);

        int a;
        while (a != 101)
        {
            a = sqlite3_step(stmt);
            //2 colonnes dans la requête
            victoires[i] = sqlite3_column_int(stmt, 0);
            victoires[i + 1] = sqlite3_column_int(stmt, 1);
            i += 2;
        };
    }
    else
    {
        cout << "Failed to open db" << endl;
    }
    sqlite3_finalize(stmt);
    sqlite3_close(db);

    cout << "Joueurs victorieux: " << endl;
    for (size_t j = 0; j < i - 2; j += 2)
    {
        cout << "Joueur: " << victoires[j] << ", Nombre de victoires: " << victoires[j + 1] << endl;
    }
}

void Requetes::TauxRetournement()
{
    sqlite3 *db;
    sqlite3_stmt *stmt;
    int i = 0;
    //Prépare un tableau pour lire sauvegardé les éléments pour pouvoir les afficher (1000 pour être large niveau taille du tableau)
    int retournement[1000];

    if (sqlite3_open("parties.db", &db) == SQLITE_OK)
    {
        string sqlstatement = "SELECT valeur, NbRetournement,((NbRetournement * 100) / (NbPartie)) as TauxDeRetournement FROM (SELECT valeur, COUNT(valeur) as NbRetournement FROM Pickominos WHERE etat == 0 GROUP BY valeur) NATURAL JOIN (SELECT COUNT(DISTINCT idPartie) as NbPartie FROM Pickominos) GROUP BY valeur;";
        sqlite3_prepare_v2(db, sqlstatement.c_str(), -1, &stmt, NULL); //Preparing the statement
        sqlite3_bind_int(stmt, 0, 0);

        int a;
        while (a != 101)
        {
            a = sqlite3_step(stmt);
            //3 colonnes dans la requête
            retournement[i] = sqlite3_column_int(stmt, 0);
            retournement[i + 1] = sqlite3_column_int(stmt, 1);
            retournement[i + 2] = sqlite3_column_int(stmt, 2);
            i += 3;
        };
    }
    else
    {
        cout << "Failed to open db" << endl;
    }
    sqlite3_finalize(stmt);
    sqlite3_close(db);
    
    for (size_t j = 0; j < i - 3; j += 3)
    {
        cout << "Pickomino: " << retournement[j] << ", Nombre de retournement: " << retournement[j + 1] << ", Taux de retournement: " << retournement[j+2] << "%" << endl;
    }
    cout << "Si le pickomino n'est pas dans la liste, son taux de retournement est de 0%" << endl;
}
