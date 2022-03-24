package server.api;

import commons.Activity;
import server.database.ActivityRepository;

public class ActivityTestRepository extends TestRepository<Activity> implements ActivityRepository {
    /**
     * find an activity according to its name(id field in the json file)
     * @param activityName the name of the activity
     * @return the matching activity
     */
    public Activity findByActivityName(String activityName) {
        for(Activity activity: repo.values()){
            if(activity.getActivityName() == activityName)return activity;
        }
        return null;
    }
}
