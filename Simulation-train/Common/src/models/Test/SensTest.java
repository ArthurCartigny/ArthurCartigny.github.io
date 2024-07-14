package models.Test;

import models.Sens;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SensTest {

    @Test
    void testToString() {

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
}