#include "Bot.hpp"

Bot::Bot()
{
}

Bot::~Bot()
{
}

int Bot::ChoisirUnDe()
{
    int choix = rand()%6 + 1;
    return choix;
}
