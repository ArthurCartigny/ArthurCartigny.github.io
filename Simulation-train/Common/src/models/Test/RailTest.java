package models.Test;

import models.Direction;
import models.PartieDuTrain;
import models.Rail;
import models.Sens;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailTest {
    public PartieDuTrain train ;

    @Test
    void getSens() {
        Sens sens1 = Sens.Horizontal;
        Sens sens2 = Sens.Vertical;
        Sens sens3 = Sens.DescenteDroite;
        Sens sens4 = Sens.DescenteGauche;
        Sens sens5 = Sens.MontéeDroite;
        Sens sens6 = Sens.MontéeGauche;
        Sens sens7 = Sens.Croisement;
        Sens sens8 = Sens.Defaut;


        assertEquals(sens1.toString(), "─");
        assertEquals(sens2.toString(), "|");
        assertEquals(sens3.toString(), "╰");
        assertEquals(sens4.toString(), "╯");
        assertEquals(sens5.toString(), "╭");
        assertEquals(sens6.toString(), "╮");
        assertEquals(sens7.toString(), "┼");
        assertEquals(sens8.toString(), "#");
    }

    @Test
    void ajouterOccupation() {

        PartieDuTrain train1 = new PartieDuTrain(0,0, Direction.Left);

        Rail rail = new Rail(0,0,Sens.Horizontal,false);
        rail.ajouterOccupation(train1);
        assertEquals(train1, rail.train);


    }

    @Test
    void retirerOccupation() {
        Rail rail = new Rail(0,0,Sens.Horizontal,false);
        rail.retirerOccupation();
        assertNull(rail.train);

    }
}