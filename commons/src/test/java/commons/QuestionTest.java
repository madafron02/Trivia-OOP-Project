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


    @Test
    void getId() {
        Question q = new Question();
        assertEquals(q.getId(),0);
    }

    @Test
    void setId() {
        Question q = new Question();
        q.setId(1);
        assertEquals(q.getId(),1);
    }


    @Test
    void getDescriptionImagePath() {
        Question q = new Question();
        q.setDescriptionImagePath("00/shower.png");
        assertEquals(q.getDescriptionImagePath(),"00/shower.png");
    }

    @Test
    void setDescriptionImagePath() {
        Question q = new Question();
        q.setDescriptionImagePath("01/shower.png");
        assertNotNull(q.getDescriptionImagePath());
    }

    @Test
    void getCorrectAnswer() {
        Question q = new Question();
        q.setCorrectAnswer("112");
        assertEquals(q.getCorrectAnswer(),"112");
    }

    @Test
    void setCorrectAnswer() {
        Question q = new Question();
        q.setCorrectAnswer("112");
        assertNotNull(q.getCorrectAnswer());
    }

    @Test
    void getImgPaths() {
        List<String>path = new ArrayList<>();
        path.add("path1");
        Question q = new Question();
        q.setImgPaths(path);
        assertEquals(q.getImgPaths(),path);
    }

    @Test
    void setImgPaths() {
        List<String>path = new ArrayList<>();
        path.add("path2");
        Question q = new Question();
        q.setImgPaths(path);
        assertNotNull(q.getImgPaths());
    }


    @Test
    void getType() {
        Question q = new Question();
        q.setType(Question.QuestionType.MORE_ENERGY);
        assertEquals(q.getType(), Question.QuestionType.MORE_ENERGY);
    }

    @Test
    void setType() {
        Question q = new Question();
        q.setType(Question.QuestionType.MORE_ENERGY);
        assertNotNull(q.getType());
    }


    @Test
    void testToString() {
        Question q = new Question();
        assertEquals(q.toString(),
                "Question{id=0, description='null', descriptionImagePath='null', " +
                        "correctAnswer='null', imgPaths=null, answers=null, type=null}");
    }
}
