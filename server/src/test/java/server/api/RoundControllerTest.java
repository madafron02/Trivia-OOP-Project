package server.api;

import commons.Player;
import commons.Question;
import commons.Round;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundControllerTest {
    private RoundController sut;
    private Round test;
    @BeforeEach
    public void init(){
        RoundTestRepository repo = new RoundTestRepository();
        List<String> answers = new ArrayList<>();
        answers.add("a1");
        answers.add("a2");
        answers.add("a3");
        Question q = new Question();
        q.setAnswers(answers);
        List<Player>p = new ArrayList<>();
        p.add(new Player("p1"));
        test = new Round(q,p);
        repo.save(test);
        sut = new RoundController(repo);
    }
    @Test
    void getAll() {
        List<Round>r = new ArrayList<>();
        r.add(test);
        assertEquals(r,sut.getAll());
    }

    @Test
    void getRoundById() {
        assertEquals(test,sut.getRoundById(test.getId()));
    }

    @Test
    void addRound() {
        List<String> answers = new ArrayList<>();
        answers.add("b1");
        answers.add("b2");
        answers.add("b3");
        Question q = new Question();
        q.setAnswers(answers);
        List<Player>p = new ArrayList<>();
        p.add(new Player("p1"));
        Round test2 = new Round(q,p);
        assertEquals("The round has been saved",sut.addRound(test2).getBody());
    }
    @Test
    void testConstructor(){
        assertNotNull(sut);
    }
}