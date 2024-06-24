#pragma once

class Pikomino
{
    //Nombre de vers sur le pickomino (entre 1 et 4)
    int nbVer;
    //Valeur du pickomino (entre 21 et 36)
    int valeur;
    //Variable permettant de voir si le pickomino est retourné dans la brochette o
    bool estRetourne = false;

public:

    Pikomino(int valeur, int nbVer);
    ~Pikomino();
    //Permet de voir le nombre de vers
    int GetNbVer();
    //Permet de voir la valeur du picko
    int GetValeur();
    //Permet de voir si le pickomino est retourné
    bool GetEstRetourne();
    //Methode retournant le pickomino
    void SetEstRetourne();
};
