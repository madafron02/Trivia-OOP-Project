package commons;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {



    @Test
    void constructorTest() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        assertNotNull(question);
    }

    @Test
    void setDescription() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        question.setDescription("desssscccc");
        assertEquals(question.getDescription(),"desssscccc");
    }

    @Test
    void getDescription() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        assertEquals(question.getDescription(),"Desc");
    }

    @Test
    void getAnswers() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        assertEquals(strings,question.getAnswers());
    }

    @Test
    void setAnswers() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        List<String> strings2 = new ArrayList<>();
        strings.add("answer3");
        strings.add("answer4");
        Question question = new Question("Desc", strings );
        question.setAnswers(strings2);
        assertEquals(strings2,question.getAnswers());
    }


    @Test
    void testEquals() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        Question question1 = new Question("Desc",strings);
        assertTrue(question.equals(question1));
    }

    @Test
    void testNotEquals() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        List<String> strings2 = new ArrayList<>();
        strings.add("answer3");
        strings.add("answer4");
        Question question = new Question("Desc", strings );
        Question question1 = new Question("Desc1",strings);
        assertFalse(question.equals(question1));
    }

    @Test
    void testHashCode() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        List<String> strings2 = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        Question question = new Question("Desc", strings );
        Question question1 = new Question("Desc",strings);
        assertEquals(question.hashCode(),question1.hashCode());
    }

    @Test
    void testNotHash() {
        List<String> strings = new ArrayList<>();
        strings.add("answer1");
        strings.add("answer2");
        List<String> strings2 = new ArrayList<>();
        strings.add("answer3");
        strings.add("answer4");
        Question question = new Question("Desc", strings );
        Question question1 = new Question("Desc1",strings2);
        assertNotEquals(question.hashCode(),question1.hashCode());
    }
}
