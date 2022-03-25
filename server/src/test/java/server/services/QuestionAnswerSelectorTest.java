package server.services;

import commons.Activity;
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
        for(int i=1;i<=4;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (i*100));
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=4;i<=8;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (i-3)*1000);
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=8;i<=12;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (i-7)*1000);
            tmp.setId(i);
            activityList.add(tmp);
        }
        for(int i=12;i<=16;i++){
            String s = "test" + i;
            Activity tmp = new Activity(s,s,s, (long) (i+10000));
            tmp.setId(i);
            activityList.add(tmp);
        }
        repo.saveAll(activityList);
        sut = new QuestionAnswerSelector(repo);
    }
    @Test
    void setAnswers() {
        sut.setAnswers(gameid);
        System.out.println(sut.getAnswers(gameid));
        assertEquals(sut.getAnswers(gameid).size(),2);
    }

    @Test
    void getAnswers() {
        sut.setAnswers(gameid);
        assertEquals(sut.getAnswers(gameid).size(),2);
    }
}