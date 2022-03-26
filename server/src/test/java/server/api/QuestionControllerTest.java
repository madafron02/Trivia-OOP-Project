package server.api;

import commons.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {
    private QuestionController sut;
    private Question test;
    @BeforeEach
    public void init(){
        QuestionTestRepository repo = new QuestionTestRepository();
        List<String> answers = new ArrayList<>();
        answers.add("a1");
        answers.add("a2");
        answers.add("a3");
        test = new Question("q1",answers);
        repo.save(test);
        sut = new QuestionController(repo);
    }
    @Test
    void getAll() {
        List<Question> t = new ArrayList<>();
        t.add(test);
        assertEquals(t,sut.getAll());
    }

    @Test
    void getById() {
        assertEquals(test,sut.getById(test.getId()).getBody());
    }

    @Test
    void add() {
        List<String> answers = new ArrayList<>();
        answers.add("b1");
        answers.add("b2");
        answers.add("b3");
        Question test2 = new Question("q2",answers);
        assertEquals(test2,sut.add(test2).getBody());
    }
}