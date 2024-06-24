#pragma once
#include "sqlite3.h"
#include <string>
#include <iostream>

using namespace std;

class Requetes
{

public:
    Requetes();
    ~Requetes();
    void AfficherScore();
    void CompterLesVictoires();
    void TauxRetournement();
};