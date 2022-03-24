package server.services;

import commons.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.api.ActivityService;
import server.api.ActivityTestRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityServiceTest {
    private ActivityService sut;
    private List<Activity> t;

    @BeforeEach
    public void init(){
        ActivityTestRepository repo = new ActivityTestRepository();
        Activity t1 = new Activity("t1","t1","t1", 1L);
        t = new ArrayList<>();
        t.add(t1);
        repo.save(t1);
        sut= new ActivityService(repo);
    }
    @Test
    void listAllActivities() {
        assertEquals(t,sut.listAllActivities());
    }

    @Test
    void addActivity() {
        Activity t2 = new Activity("t2","t2","t2", 2L);
        assertEquals(t2,sut.addActivity(t2).getBody());
    }
    boolean checkContents(Activity a,Activity b){
        return a.getActivityName() == b.getActivityName() &&
                a.getConsumption() == b.getConsumption() &&
                a.getTitle() == b.getTitle() &&
                a.getImgPath() == b.getImgPath();
    }
    //TODO:the program throws FILENOTFOUND exception when trying to call ParseJson here
    // no idea what's the cause so still have to fix the io exception problem
    /*
        @Test
    void database() throws FileNotFoundException {
        sut.database();
        Activity firstOne = new Activity("38-hairdryer","38/hairdryer.png",
                "Using a hairdryer for an hour",1200L);
        Activity secondOne = new Activity("38-leafblower","38/leafblower.png",
        "Using a leafblower for 15 minutes",183L);
        List<Activity>activities = sut.listAllActivities();
        assertTrue(checkContents(activities.get(1),firstOne));
        assertTrue(checkContents(activities.get(2),secondOne));
    }
     */

}