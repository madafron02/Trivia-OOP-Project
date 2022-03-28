package commons;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Test
    void constructorTest(){
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
    }


    @Test
    void getQuestion() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        assertEquals(round.getQuestion(),question);
    }

    @Test
    void setQuestion() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        round.setQuestion(question2);
        assertEquals(round.getQuestion(),question2);
    }


    @Test
    void getPlayers() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        round.setQuestion(question2);
        assertEquals(round.getPlayers(),players);
    }

    @Test
    void setPlayers() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        List<Player> players2 = new ArrayList<>();
        Player player3 = new Player("Bob");
        Player player4 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        round.setPlayers(players2);
        assertEquals(round.getPlayers(),players2);
    }


    @Test
    void testHashCode() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        List<Player> players2 = new ArrayList<>();
        Player player3 = new Player("Bob");
        Player player4 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        Round round1 = new Round(question,players);
        assertEquals(round.hashCode(),round1.hashCode());
    }

    @Test
    void testNotHashCode() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        List<Player> players2 = new ArrayList<>();
        Player player3 = new Player("Bob");
        Player player4 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        Round round1 = new Round(question,players2);
        assertNotEquals(round.hashCode(),round1.hashCode());
    }

    @Test
    void testEquals() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        List<Player> players2 = new ArrayList<>();
        Player player3 = new Player("Bob");
        Player player4 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        Round round1 = new Round(question,players);
        assertTrue(round.equals(round1));
    }

    @Test
    void testNotEquals() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question2 = new Question("Desc", strings );
        List<Player> players = new ArrayList<>();
        Player player = new Player("Bob");
        Player player2 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        List<Player> players2 = new ArrayList<>();
        Player player3 = new Player("Bob");
        Player player4 = new Player("Bobi");
        players.add(player);
        players.add(player2);
        Round round = new Round(question,players);
        Round round1 = new Round(question,players2);
        assertFalse(round.equals(round1));
    }

}
