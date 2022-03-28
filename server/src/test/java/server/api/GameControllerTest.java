package server.api;
import commons.Game;
import commons.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    private GameController sut;
    private Game test;
    @BeforeEach
    public void init(){
        GameTestRepository repo = new GameTestRepository();
        List<Player>players = new ArrayList<>();
        players.add(new Player("t1"));
        players.add(new Player("t2"));
        test = new Game(players);
        repo.save(test);
        sut = new GameController(repo);
    }
    @Test
    void getAll() {
        List<Game>actual = new ArrayList<>();
        actual.add(test);
        assertEquals(actual,sut.getAll());
    }

    @Test
    void getGame() {
        long id = test.getId();
        assertEquals(test,sut.getGame(id).getBody());
    }

    @Test
    void addGame() {
        List<Player>players = new ArrayList<>();
        players.add(new Player("t3"));
        players.add(new Player("t4"));
        Game test2 = new Game(players);
        assertEquals(test2,sut.addGame(test2).getBody());
    }

    @Test
    void deleteGameById() {
        sut.deleteGameById(test.getId());
        assertTrue(sut.getAll().isEmpty());
    }

    @Test
    void clear() {
        sut.addToCurrentGame(new Player("t3"));
        sut.clear();
        assertTrue(sut.getCurrentGame().getBody().getPlayers().isEmpty());
    }

    @Test
    void getCurrentGame() {
        sut.addToCurrentGame(test.getPlayers().get(0));
        sut.addToCurrentGame(test.getPlayers().get(1));
        assertEquals(sut.getCurrentGame().getBody().getPlayers(),test.getPlayers());
    }

    @Test
    void addToCurrentGame() {
        List<Player>players = new ArrayList<>();
        Player t3 = new Player("t3");
        players.add(t3);
        sut.addToCurrentGame(t3);
        assertEquals(players,sut.getCurrentGame().getBody().getPlayers());
    }

    @Test
    void testConstructor(){
        assertNotNull(sut);
    }
    @Test
    void getCurrentStatus() {
        sut.setCurrentStatus(false);
        assertFalse(sut.getCurrentStatus().getBody());
    }

    @Test
    void setCurrentStatus() {
        sut.setCurrentStatus(false);
        assertFalse(sut.getCurrentStatus().getBody());
    }
}