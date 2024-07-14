package models.Test;

import fileReader.LectureFichier;
import models.Direction;
import models.Map;
import models.Train;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainTest {

    @Test
    void demarrerLeTrain() {
        Map map = new Map(new LectureFichier("Ressources/map.map"));
        Train train = new Train(1,1,5, Direction.Left);

        assertNull(train.getMap());
        for (int i = 0; i < 5; i++) {
            assertNull(train.wagons[i]);
        }

        train.demarrerLeTrain(map);

        assertNotNull(train.getMap());
        for(int i = 0; i<5;i++)
        {
            assertNotNull(train.wagons[i]);
        }

    }

    @Test
    void creationWagons() {
        Map map = new Map(new LectureFichier("Ressources/map.map"));
        Train train = new Train(1,1,5, Direction.Left);
        train.setMap(map);

        for (int i = 0; i < 5; i++) {
            assertNull(train.wagons[i]);
        }
        train.creationWagons();
        for(int i = 0; i<5;i++)
        {
            assertNotNull(train.wagons[i]);
        }

    }

    @Test
    void deplacementTrain() {
        Map map = new Map(new LectureFichier("Ressources/map.map"));
        Train train = new Train(6,2,1, Direction.Left);
        train.setMap(map);
        train.creationWagons();
        System.out.println(train.locomotive.positionX);
        assertEquals(4, train.locomotive.positionX);
        assertEquals(2, train.locomotive.positionY);
        assertEquals(5, train.wagons[0].positionX);
        assertEquals(2, train.wagons[0].positionY);
        train.deplacementTrain();
        assertEquals(3, train.locomotive.positionX);
        assertEquals(2, train.locomotive.positionY);
        assertEquals(4, train.wagons[0].positionX);
        assertEquals(2, train.wagons[0].positionY);

    }

    @Test
    void setMap() {
        Train train = new Train(1,1,5, Direction.Left);
        assertNull(train.getMap());
        train.setMap(new Map(new LectureFichier("Ressources/map.map")));
        assertNotNull(train.getMap());
    }
}