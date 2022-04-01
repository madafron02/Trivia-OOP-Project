package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Game entity for the database containing the list of players in the game.
 * Will be used in the future to keep track of parallel games and use the players from
 * each of them to sort and put to the corresponding in-game leaderboard/winners screen
 */
@Entity
public class Game implements Serializable,Reachable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;          //probably will add the list of rounds as an attribute in the future

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    public Game() {
    }

    /**
     * Constructor for a game
     * @param players list of players for this game
     */
    public Game(List<Player> players) {
        this.players = players;
    }

    /**
     * A getter for the game.
     * @return the list of players playing this particular game
     */
    public List<Player> getPlayers() {
        return players;
    }



    /**
     * A setter for this game allowing changing the player list
     * @param players (list of this game's players)
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", players=" + players +
                '}';
    }

    /**
     * Hashing method for this game
     * @return the hash int of this game object
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     *
     * @param obj (any object to compare to this game)
     * @return whether the object is equal to this game
     */
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    /**
     * A getter for the game.
     * @return the unique id of this game
     */
    @Override
    public long getId() {
        return id;
    }


}
