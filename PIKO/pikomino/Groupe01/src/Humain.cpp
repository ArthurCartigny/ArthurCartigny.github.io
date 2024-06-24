#include "Humain.hpp"

Humain::Humain()
{
}
Humain::~Humain()
{
}

int Humain::ChoisirUnDe()
{
    int choix;
    cin >> choix;
    if(cin.fail() || (choix < 0 && choix > 6))
    {
      cerr << "Entrez une valeur entre 1 et 6" << endl;
      choix = 0;    
    }
    cin.clear(); cin.ignore(INT32_MAX, '\n');
    return choix;
}