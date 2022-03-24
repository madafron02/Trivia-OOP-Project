package server.api;
import commons.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    private PlayerController sut;
    private Player test;
    @BeforeEach
    public void init(){
        TestPlayerRepository repo = new TestPlayerRepository();
        test = new Player("yellow");
        repo.save(test);
        sut = new PlayerController(repo);
    }
    @Test
    void getAll() {
        List<Player>players = new ArrayList<>();
        players.add(test);
        assertEquals(players,sut.getAll());
    }

    @Test
    void getPlayer() {
        assertEquals(test,sut.getPlayer(test.getId()).getBody());
    }

    @Test
    void addPlayer() {
        Player test2 = new Player("orange");
        assertEquals(test2,sut.addPlayer(test2).getBody());
    }

    @Test
    void getBest() {
        Player test3 = new Player("blue");
        test3.setPoints(5);
        sut.addPlayer(test3);
        assertEquals(test3,sut.getBest().getBody());
    }

    @Test
    void top() {
        List<Player> actual = new ArrayList<>();
        for (int i = 9; i >=1 ; i--) {
            Player tmp = new Player("test"+i);
            tmp.setId(i);
            tmp.setPoints(i);
            actual.add(tmp);
            sut.addPlayer(tmp);
        }
        actual.add(test);
        assertEquals(actual,sut.top());
    }
}