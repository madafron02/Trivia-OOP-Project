package commons;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

import java.io.Serializable;


@Entity
public class Player implements Serializable,Reachable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int points;
    private int place;          //place in the leaderboard
    private boolean multi;

    /**
     * Get the status of the player
     * @return the status of the player
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Set the status of the player
     * @param status status of the player
     */
    public void setStatus(StatusType status) {
        this.status = status;
    }

    public enum StatusType {
        READY,
        NOT_READY,
        ABORTED,
        NO_ANSWER,
    }
    private StatusType status;
    public Player() {
    }

    /**
     * create a new player
     * @param name name of the player
     */
    public Player(String name) {
        this.name = name;
        this.points = 0;
        this.place = 0;
        this.multi = false;
        this.status = StatusType.NOT_READY;
    }

    /**
     * check which mode the player is currently in
     * @return iff the player choose mutiplayer mode
     */
    public boolean isMulti() {
        return multi;
    }

    /**
     * set the mode of the player
     * @param multi true if the player is in multiplayer mode
     */
    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    /**
     * get the rank of the player
     * @return the rank of the player
     */
    public int getPlace() {
        return place;
    }

    /**
     * the the rank of the player
     * @param place the rank of the player
     */
    public void setPlace(int place) {
        this.place = place;
    }

    /**
     * set the points of the player
     * @param addition addition points earned by the player
     */
    public void setPoints(int addition){
        this.points+=addition;
    }

    /**
     * get the point of the player
     * @return point of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * get the name of the player
     * @return name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the player
     * @param name name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * check if the other object is identical to this player
     * @param obj the other object
     * @return true iff the other object is the same as this player
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * calculate the hashcode of the player
     * @return the hashcode
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * convert the player to a string representation
     * @return the string representaion of the player
     */
    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", points=" + points +
                ", place=" + place +
                ", multi=" + multi +
                ", status=" + status +
                '}';
    }

    /**
     * get the id of the player
     * @return id of the player
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * set the if of the player
     * @param id id of the player
     */
    public void setId(long id) {
        this.id = id;
    }
}
