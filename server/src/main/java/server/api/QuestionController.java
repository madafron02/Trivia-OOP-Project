package server.api;
import commons.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.QuestionRepository;
import server.services.QuestionAnswerSelector;

import java.util.List;

@RestController

@RequestMapping("/api/question")

public class QuestionController {

    private final QuestionRepository questionRepository;
    private QuestionAnswerSelector questionAnswerSelector;

    public QuestionController(QuestionRepository questionRepository,
                              QuestionAnswerSelector questionAnswerSelector) {
        this.questionRepository = questionRepository;
        this.questionAnswerSelector = questionAnswerSelector;
    }

    @GetMapping(path = {"/",""})
    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable("id") long id) {
        if (id < 0 || !questionRepository.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(questionRepository.getById(id));
    }

    /**
     * take the gameId and roundNumber from http request, return the corresponding question
     * @param gameId indicates the game
     * @param roundNumber indicates the round
     * @return the matched question(if there is any)
     */
    @GetMapping("/getQ/{gameId}/{roundNumber}")
    public ResponseEntity<Question> getQuestion(@PathVariable("gameId") long gameId,
                                                @PathVariable("roundNumber") int roundNumber) {
        Question res = questionAnswerSelector.getQuestion(gameId,roundNumber);
        while(res == null)questionAnswerSelector.getQuestion(gameId,roundNumber);
        return ResponseEntity.ok(res);
    }

    public Question add(Question question) {
        return questionRepository.save(question);
    }
    /**
     * preprocess all the questions for a certain game
     * @param gameId indicates the game
     */
    @GetMapping("/setQ/{gameId}")
    public void setQuestion(@PathVariable("gameId") long gameId) {
        questionAnswerSelector.setGameQuestions(gameId);
    }
}
