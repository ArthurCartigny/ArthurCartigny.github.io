package models.Test;

import fileReader.LectureFichier;
import models.*;
import org.junit.jupiter.api.Test;

import static models.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class PartieDuTrainTest {

    @Test
    void deplacement() {
        LectureFichier reader = new LectureFichier("Ressources/map.map");
        Map map = new Map(reader);

        PartieDuTrain partieDuTrain = new PartieDuTrain(1,1,Top);
        partieDuTrain.deplacement(map);

        assertEquals(Right, partieDuTrain.directionDuTrain);
     }

    @Test
    void changerDirection() {

        PartieDuTrain partieDuTrain = new PartieDuTrain(0,0,Left);

        Rail rail1 = new Rail(0,0, Sens.MontéeDroite,false);
        partieDuTrain.setEmplacement(rail1);
        partieDuTrain.changerDirection();
        assertEquals(Direction.Bottom, partieDuTrain.directionDuTrain);

        Rail rail2 = new Rail(0,0, Sens.DescenteDroite,false);
        partieDuTrain.setEmplacement(rail2);
        partieDuTrain.changerDirection();
        assertEquals(Right, partieDuTrain.directionDuTrain);

        Rail rail3 = new Rail(0,0, Sens.DescenteGauche,false);
        partieDuTrain.setEmplacement(rail3);
        partieDuTrain.changerDirection();
        assertEquals(Top, partieDuTrain.directionDuTrain);

        Rail rail4 = new Rail(0,0, Sens.MontéeGauche,false);
        partieDuTrain.setEmplacement(rail4);
        partieDuTrain.changerDirection();
        assertEquals(Left, partieDuTrain.directionDuTrain);

    }

    @Test
    void avancerLeTrain() {
        PartieDuTrain partieDuTrain = new PartieDuTrain(0,0,Left);
        PartieDuTrain partieDuTrain2 = new PartieDuTrain(0,0,Right);
        PartieDuTrain partieDuTrain3 = new PartieDuTrain(0,0,Top);
        PartieDuTrain partieDuTrain4 = new PartieDuTrain(0,0,Bottom);

        partieDuTrain.avancerLeTrain();
        partieDuTrain2.avancerLeTrain();
        partieDuTrain3.avancerLeTrain();
        partieDuTrain4.avancerLeTrain();

        assertEquals(-1,partieDuTrain.positionX);
        assertEquals(1,partieDuTrain2.positionX);
        assertEquals(-1,partieDuTrain3.positionY);
        assertEquals(1,partieDuTrain4.positionY);
    }
}