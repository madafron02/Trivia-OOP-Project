package server.api;


import commons.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import server.ActivityParse;
import server.database.ActivityRepository;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public class ActivityService {

    private ActivityRepository activityRepository;

    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<Activity> listAllActivities() {
        return activityRepository.findAll();
    }

    public ResponseEntity<Activity> addActivity(Activity activity) {
        Activity saved = activityRepository.save(activity);
        return ResponseEntity.ok(saved);
    }

    @PostConstruct
    void database() throws FileNotFoundException {
        ActivityParse activityBank = new ActivityParse();
        List<Activity> activities = activityBank.getActivities();
        for (Activity a : activities) {
            addActivity(a);
        }
    }

}

