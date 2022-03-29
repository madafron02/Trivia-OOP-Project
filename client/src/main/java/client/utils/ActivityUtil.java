package client.utils;

import commons.Activity;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ActivityUtil {

    private static final String SERVER = "http://localhost:8080/";

    /**
     * the List of activities in client might be useful for single player,
     * but for now we can just send requests to server
     */
    //private List<Activity> activities;

    /** TODO make it so you can choose how many answers you want
     *  retrieves answers from the server
     * @return a List of answers
     */
    public List<Activity> getAnswers(Long gameId){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/answers/" + gameId) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<List<Activity>>() {});
    }

    /**
     * adds an Activity to the repository and to the list
     * @param activity
     * @return the same activity
     */
    public Activity addActivity(Activity activity){
        activity.setPowerLevel();
        //activities.add(activity);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/post") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(activity, APPLICATION_JSON), Activity.class);
    }

    /*public Optional<Activity> getByID(long id){
        return activities.stream().filter(x -> x.getId() == id).findFirst();
    }*/

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
     *  retrieves all activities from the repository
     * @return a List of Activity
     */
    public List<Activity> getAll(){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<List<Activity>>() {});
    }

    /**
     * Deletes an Activity from the repository and the List
     * @param id
     * @return the deleted Activity
     */
    public Activity deleteByID(long id){
        //activities = activities.stream().filter(x -> x.getId() != id)
        //        .collect(Collectors.toList());
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/delete/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .delete(new GenericType<Activity>() {});
    }
}
