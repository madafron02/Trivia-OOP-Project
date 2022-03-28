package commons;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void constructorTest() {
        Player player1 = new Player("Bob");
        assertNotNull(player1);
    }

    @Test
    void setPoints() {
        Player player1 = new Player("Bob");
        player1.setPoints(100);
        assertEquals(player1.getPoints(),100);
    }

    @Test
    void getPoints() {
        Player player1 = new Player("Bob");
        assertEquals(player1.getPoints(),0);
    }

    @Test
    void getName() {
        Player player1 = new Player("Bob");
        assertEquals(player1.getName(),"Bob");
    }

    @Test
    void setName() {
        Player player1 = new Player("Bob");
        player1.setName("NotBob");
        assertEquals(player1.getName(),"NotBob");
    }

    @Test
    void hashCodeTest() {
        Player player1 = new Player("Bob1");
        Player player2 = new Player("Bob1");
        assertEquals(player1.hashCode(),player2.hashCode());
    }

    @Test
    void testHashCode() {
        Player player1 = new Player("Bob1");
        Player player2 = new Player("Bob");
        assertNotEquals(player1.hashCode(),player2.hashCode());

    }

    @Test
    void equalTest() {
        Player player1 = new Player("Bob1");
        Player player2 = new Player("Bob1");
        assertTrue(player1.equals(player2));
    }

    @Test
    void notEqualTest() {
        Player player1 = new Player("Bob1");
        Player player2 = new Player("Bob2");
        assertFalse(player1.equals(player2));
    }


}
