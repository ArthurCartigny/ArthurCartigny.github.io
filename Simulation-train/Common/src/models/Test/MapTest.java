package models.Test;

import fileReader.LectureFichier;
import models.Map;
import models.Rail;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    @Test
    void lireFichier() {
        LectureFichier reader = new LectureFichier("Ressources/map.map");
        Map map = new Map();

        map.lireFichier(reader);
        assertEquals(25, map.tailleX);
        assertEquals(15, map.tailleY);
        assertEquals(2, map.trains.size());

    }

    @Test
    void updateMap() {
        LectureFichier reader = new LectureFichier("Ressources/map.map");
        //La méthode updateMap est appelée lors de la création des trains donc nous vérifions ici qu'il y ait bien des cases occupées et des cases inoccupées
        Map map = new Map(reader);
        System.out.println(map.trains.get(0).locomotive.positionX + " , " + map.trains.get(0).locomotive.positionY);
        Rail rail = (Rail)map.map[1][2];
        assertNotNull(rail);

        Rail rail2 = (Rail)map.map[5][8];
        assertNull(rail2.train);
    }
}