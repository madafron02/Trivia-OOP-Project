package server.api;

import commons.Activity;
import server.database.ActivityRepository;

public class ActivityTestRepository extends TestRepository<Activity> implements ActivityRepository {
    public Activity findByActivityName(String activityName) {
        for(Activity activity: repo.values()){
            if(activity.getActivityName() == activityName)return activity;
        }
        return null;
    }
}
