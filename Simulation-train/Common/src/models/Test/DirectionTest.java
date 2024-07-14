package models.Test;

import models.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    void testToString() {
        Direction dir1 = Direction.Bottom;
        Direction dir2 = Direction.Left;
        Direction dir3 = Direction.Right;
        Direction dir4 = Direction.Top;

        assertEquals(dir1.toString(), "B");
        assertEquals(dir2.toString(), "L");
        assertEquals(dir3.toString(), "R");
        assertEquals(dir4.toString(), "T");
    }
}