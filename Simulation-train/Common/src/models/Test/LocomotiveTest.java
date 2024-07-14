package models.Test;

import fileReader.LectureFichier;
import models.Locomotive;
import models.Map;
import models.PartieDuTrain;
import models.Rail;
import org.junit.jupiter.api.Test;

import static models.Direction.*;
import static org.junit.jupiter.api.Assertions.*;

class LocomotiveTest {

    @Test
    void checkCollisions() {
        LectureFichier reader = new LectureFichier("Ressources/map.map");
        Map map = new Map(reader);

        Locomotive locomotive1 = new Locomotive(2,7, Right);
        Locomotive locomotive2 = new Locomotive(5,7, Right);
        PartieDuTrain part = new PartieDuTrain(3,7, Left);
        Rail rail = (Rail)map.map[3][7];
        rail.ajouterOccupation(part);
        assertTrue(locomotive1.checkCollisions(map));
        assertFalse(locomotive2.checkCollisions(map));

        Locomotive locomotive3 = new Locomotive(2,6, Top);
        PartieDuTrain part2 = new PartieDuTrain(2,5, Bottom);
        Locomotive locomotive4 = new Locomotive(2,6, Bottom);
        Rail rail2 = (Rail)map.map[2][5];
        rail2.ajouterOccupation(part2);
        assertTrue(locomotive3.checkCollisions(map));
        assertFalse(locomotive4.checkCollisions(map));
    }

    @Test
    void calculDuProchainRail() {
        LectureFichier reader = new LectureFichier("Ressources/map.map");
        Map map = new Map(reader);

        Locomotive locomotive1 = new Locomotive(2,6,Top);
        Rail railtest = locomotive1.calculDuProchainRail(map);
        assertEquals(2, railtest.positionX);
        assertEquals(5, railtest.positionY);

        Locomotive locomotive2 = new Locomotive(2,6, Bottom);
        Rail railtest2 = locomotive2.calculDuProchainRail(map);
        assertEquals(2, railtest2.positionX);
        assertEquals(7, railtest2.positionY);

        Locomotive locomotive3 = new Locomotive(2,6, Right);
        Rail railtest3 = locomotive3.calculDuProchainRail(map);
        assertEquals(3, railtest3.positionX);
        assertEquals(6, railtest3.positionY);

        Locomotive locomotive4 = new Locomotive(2,6, Left);
        Rail railtest4 = locomotive4.calculDuProchainRail(map);
        assertEquals(1, railtest4.positionX);
        assertEquals(6, railtest4.positionY);
    }
}