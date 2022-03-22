package commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Game {

    private long id;
    private List<Round> rounds;
    private List<Player> players;

    public Game() {
    }

    public Game(List<Round> rounds, List<Player> players) {
        id = 0;
        this.rounds = rounds;
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public String toString() {
        String answer = "Game id: " + this.id + "\nPlayers:";
        for (Player player : players) {
            answer += player.getName() + "\n";
        }
        return answer;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }


}
