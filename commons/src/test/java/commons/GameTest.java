package commons;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testConstructor() {
        Game g = new Game(new ArrayList<>());
        assertNotNull(g);
    }
    @Test
    void getPlayers() {
        Player p = new Player("ppp");
        List<Player> playersList = new ArrayList<>();
        playersList.add(p);
        Game g = new Game(playersList);
        assertEquals(g.getPlayers(),playersList);
    }

    @Test
    void setPlayers() {
        Player p = new Player("ppp");
        List<Player> playersList = new ArrayList<>();
        playersList.add(p);
        Game g = new Game(new ArrayList<>());
        g.setPlayers(playersList);
        assertEquals(g.getPlayers(),playersList);
    }

    @Test
    void testToString() {
        Player p = new Player("ppp");
        List<Player> playersList = new ArrayList<>();
        playersList.add(p);
        Game g = new Game(playersList);
        assertEquals(g.toString(),"Game{id=0, players=[Player{id=0, name='ppp'," +
                " points=0, place=0, multi=false, status=NOT_READY}]}");
    }

    @Test
    void testHashCode() {
        Game g1 = new Game(new ArrayList<>());
        Game g2 = new Game(new ArrayList<>());
        assertEquals(g1.hashCode(),g2.hashCode());
    }

    @Test
    void testEquals() {
        Player p = new Player("ppp");
        List<Player> playersList = new ArrayList<>();
        playersList.add(p);
        Game g1 = new Game(playersList);
        Game g2 = new Game(playersList);
        Game g3 = new Game(new ArrayList<>());
        assertEquals(g1,g2);
        assertNotEquals(g1,g3);
    }

}