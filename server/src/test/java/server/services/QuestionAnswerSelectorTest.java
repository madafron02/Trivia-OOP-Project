package server.services;

import commons.Activity;
import commons.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.api.ActivityTestRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionAnswerSelectorTest {
    private QuestionAnswerSelector sut;
    private static long gameid = 0;
    private List<Activity> activityList;
    @BeforeEach
    public void init(){
        ActivityTestRepository repo = new ActivityTestRepository();
        activityList = new ArrayList<>();
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
        repo.saveAll(activityList);
        sut = new QuestionAnswerSelector(repo);
    }
    @Test
    void setAnswers() {
        sut.setGameAnswers(gameid);
        System.out.println(sut.getGameAnswers(gameid));
        assertEquals(sut.getGameAnswers(gameid).size(),20);
    }

    @Test
    void getAnswers() {
        sut.setGameAnswers(gameid);
        assertEquals(sut.getGameAnswers(gameid).size(),20);
    }

    @Test
    void setSeparated() {
        sut.setSeparated();
        List<List<Activity>>result = sut.getSeparated();
        for(List<Activity> res : result){
            for(int i=1;i<res.size();i++){
                assertEquals(res.get(i).getPowerLevel(),res.get(i-1).getPowerLevel());
            }
        }
    }
    @Test
    void setGameQuestions() {
        sut.setGameQuestions(gameid);
        for (int i = 0; i < 20; i++) {
            Question q = sut.getQuestion(gameid,i);
            assertNotNull(q);
        }
    }

    @Test
    void getMoreEnergyQuestion() {
        sut.setGameAnswers(gameid);
        Question q = sut.getMoreEnergyQuestion(0,0);
        int ans = Integer.parseInt(q.getCorrectAnswer()) - 1;
        for(int j=0;j<3;j++){
            assertTrue(q.getAnswers().get(ans).compareTo(q.getAnswers().get(j))<=0);
        }
    }

    @Test
    void getEnergyGuessQuestion() {
        sut.setGameAnswers(gameid);
        Question q = sut.getEnergyGuessQuestion(0,0);
        assertNotNull(q);
    }

    @Test
    void getComparisonQuestion() {
        sut.setGameAnswers(gameid);
        Question q = sut.getComparisonQuestion(0,0);
        String base = q.getDescription();
        int index = Integer.parseInt(q.getCorrectAnswer())-1;
        for(int i=0;i<3;i++){
            if(i == index)assertTrue(base.compareTo(q.getAnswers().get(i))>=0);
            else assertTrue(base.compareTo(q.getAnswers().get(i))<=0);
        }
    }

    @Test
    void getOpenQuestion() {
        sut.setGameAnswers(gameid);
        Question q = sut.getOpenQuestion(0,0);
        double tmp = Integer.parseInt(q.getDescription().replace("test",""));
        if(tmp>100&&tmp<=200)tmp+=1000;
        else if(tmp<=300)tmp+=7500;
        else if(tmp<=400)tmp+=15000;
        assertEquals(tmp,Double.parseDouble(q.getCorrectAnswer()));
    }

    @Test
    void getQuestion() {
        sut.setGameAnswers(gameid);
        for(int i=0;i<20;i++){
            assertNotNull(sut.getQuestion(gameid,i));
        }
    }

    @Test
    void getSeparated() {
        sut.setSeparated();
        assertNotNull(sut.getSeparated());
    }
}