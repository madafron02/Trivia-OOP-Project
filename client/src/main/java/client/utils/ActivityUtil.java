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

    /** TODO make it so you can choose how many answers you want
     *  retrieves answers from the server
     * @return a List of answers
     */
    public List<Activity> getAnswers(Long gameId){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/answers/" + gameId) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<>() {
                });
    }

    /**
     * adds an Activity to the repository and to the list
     * @param activity
     * @return the same activity
     */
    public Activity addActivity(Activity activity){
        activity.setPowerLevel();
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/post") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(activity, APPLICATION_JSON), Activity.class);
    }


    /**
     *  retrieves an activity from the repository
     * @param id id of the activity
     * @return an activity from the Repository matching the ID
     */
    public Activity getByIDRepo(long id){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<>() {
                });
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
                .get(new GenericType<>() {
                });
    }

    /**
     * Deletes an Activity from the repository and the List
     * @param id id of the updated activity
     * @return the deleted Activity
     */
    public Activity deleteByID(long id){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/delete/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .delete(new GenericType<>() {
                });
    }

    /**
     * used to update the consumption of an Activity
     * @param id id of the updated activity
     * @param fileP new value
     * @return true if successful
     */
    public Activity updateFileP(long id, String fileP){
        var newA = getByIDRepo(id);
        newA.setActivityName(fileP);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/update/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .put(Entity.entity(newA, APPLICATION_JSON), new GenericType<>() {
                });
    }
    /**
     * used to update the consumption of an Activity
     * @param id id of the updated activity
     * @param imageP new value
     * @return true if successful
     */
    public Activity updateImageP(long id, String imageP){
        var newA = getByIDRepo(id);
        newA.setImgPath(imageP);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/update/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .put(Entity.entity(newA, APPLICATION_JSON), new GenericType<>() {
                });
    }
    /**
     * used to update the consumption of an Activity
     * @param id id of the updated activity
     * @param title new value
     * @return true if successful
     */
    public Activity updateTitle(long id, String title){
        var newA = getByIDRepo(id);
        newA.setTitle(title);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/update/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .put(Entity.entity(newA, APPLICATION_JSON), new GenericType<>() {
                });
    }
    /**
     * used to update the consumption of an Activity
     * @param id id of the updated activity
     * @param consum new value
     * @return true if successful
     */
    public Activity updateConsum(long id, long consum){
        var newA = getByIDRepo(id);
        newA.setConsumption(consum);
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("/api/activity/update/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .put(Entity.entity(newA, APPLICATION_JSON), new GenericType<>() {
                });
    }
}
