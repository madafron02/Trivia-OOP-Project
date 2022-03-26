package server.api;

import commons.Activity;
import commons.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.services.QuestionAnswerSelector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionControllerTest {
    private QuestionController sut;
    private List<Question> test;
    @BeforeEach
    public void init(){
        QuestionTestRepository Qrepo = new QuestionTestRepository();
        ActivityTestRepository Arepo = new ActivityTestRepository();
        List<Activity>activityList = new ArrayList<>();
        for(int i=1;i<=100;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (i));
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=101;i<=200;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (1000+i));
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=201;i<=300;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (7500+i));
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=301;i<=400;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (15000+i));
            tmp.setId(i);
            activityList.add(tmp);
        }
        Arepo.saveAll(activityList);
        QuestionAnswerSelector QAselector = new QuestionAnswerSelector(Arepo);
        QAselector.setGameQuestions(0L);
        for(int i=0;i<10;i++){
            Question q = QAselector.getQuestion(0,i);
            q.setId(i);
            test.add(q);
        }
        Qrepo.saveAll(test);
        sut = new QuestionController(Qrepo,QAselector);
    }
    @Test
    void getAll() {
        assertEquals(test,sut.getAll());
    }

    @Test
    void getById() {
        for(int i=0;i<20;i++){
            assertEquals(test.get(i),sut.getById(i));
        }
    }

    @Test
    void add() {
        Question q = sut.getQuestion(0,10).getBody();
        assertEquals(q,sut.add(q).getBody());
    }
}