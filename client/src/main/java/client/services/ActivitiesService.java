package client.services;

import commons.Activity;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ActivitiesService {
    private static final String SERVER = "http://localhost:8080/";

    private List<Activity> activities;

    private List<Activity> low;
    private List<Activity> mid;
    private List<Activity> high;
    private List<Activity> deyum;

    public ActivitiesService(){}

    public List<Activity> getActivities() {
        return activities;
    }

    public List<Activity> getLow() {
        return low;
    }

    public List<Activity> getMid() {
        return mid;
    }

    public List<Activity> getHigh() {
        return high;
    }

    public List<Activity> getDeyum() {
        return deyum;
    }

    /**
     * Retrieves the activities from the repository and assigns each one a power level
     * as well as counting all activities according to their power level
     * Puts the activities into a list
     */
    public void updateActivities(){
        activities = ClientBuilder.newClient(new ClientConfig())
                .target(SERVER).path("api/activity")
                .request(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .get(new GenericType<List<Activity>>() {});
        activities.forEach(x -> x.setPowerLevel());
        low = activities.stream()
                .filter(x -> x.getPowerLevel()
                        .equals("low")).collect(Collectors.toList());
        mid = activities.stream()
                .filter(x -> x.getPowerLevel()
                        .equals("mid")).collect(Collectors.toList());
        high = activities.stream()
                .filter(x -> x.getPowerLevel()
                        .equals("high")).collect(Collectors.toList());
        deyum = activities.stream()
                .filter(x -> x.getPowerLevel()
                        .equals("deyum")).collect(Collectors.toList());
    }

    /**
     * adds an Activity to the repository and to the list
     * @param activity
     * @return the same activity
     */
    public Activity addActivity(Activity activity){
        activity.setPowerLevel();
        activities.add(activity);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/post") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(activity, APPLICATION_JSON), Activity.class);
    }

    public Optional<Activity> getByID(long id){
        return activities.stream().filter(x -> x.getId() == id).findFirst();
    }

    /**
     *  retrieves an activity from the repository, not the List
     * @param id
     * @return an activity from the Repository matching the ID
     */
    public Activity getByIDRepo(long id){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<Activity>() {});
    }

    /**
     * Deletes an Activity from the repository and the List
     * @param id
     * @return the deleted Activity
     */
    public Activity deleteByID(long id){
        activities = activities.stream().filter(x -> x.getId() != id).collect(Collectors.toList());
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/delete/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .delete(new GenericType<Activity>() {});
    }

}
