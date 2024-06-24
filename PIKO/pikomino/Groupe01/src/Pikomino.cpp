#include "Pikomino.hpp"

Pikomino::Pikomino(int valeur, int nbVer)
{
    this->valeur = valeur;
    this->nbVer = nbVer;
}

Pikomino::~Pikomino()
{
}

int Pikomino::GetNbVer()
{
    return this->nbVer;
}

int Pikomino::GetValeur()
{
    return this->valeur;
}

bool Pikomino::GetEstRetourne()
{
    return this->estRetourne;
}

void Pikomino::SetEstRetourne()
{
    this->estRetourne = true;
}
