package server.api;

import commons.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityControllerTest {
    private ActivityController sut;
    private List<Activity> t;
    @BeforeEach
    public void init(){
        ActivityTestRepository repo = new ActivityTestRepository();
        Activity t1 = new Activity("t1","t1","t1", 1L);
        t = new ArrayList<>();
        t.add(t1);
        repo.save(t1);
        ActivityService service= new ActivityService(repo);
        sut = new ActivityController(service);
    }
    @Test
    void testConstructor(){
        assertNotNull(sut);
    }
    @Test
    void getAll() {
        assertEquals(t,sut.getAll());
    }

    @Test
    void save() {
        Activity t2 = new Activity("t2","t2","t2", 2L);
        System.out.println(t2);
        assertEquals(t2,sut.save(t2).getBody());
    }
}