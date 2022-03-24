package server.api;
import commons.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.ActivityRepository;
import server.database.QuestionRepository;
import server.services.QuestionAnswerSelector;

import java.util.List;

@RestController

@RequestMapping("/api/question")

public class QuestionController {

    private final QuestionRepository questionRepository;
    private final ActivityRepository activityRepository;
    private QuestionAnswerSelector questionAnswerSelector;

    public QuestionController(QuestionRepository questionRepository,
                              ActivityRepository activityRepository,
                              QuestionAnswerSelector questionAnswerSelector) {
        this.questionRepository = questionRepository;
        this.activityRepository = activityRepository;
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

    @PostMapping("/new")
    public ResponseEntity<Question> newQuestion(@RequestBody Question question) {
        switch (question.getType()){
            case MORE_ENERGY:
                question = questionAnswerSelector.getMoreEnergyQuestion();
                break;
            case ENERGY_GUESS:
                question = questionAnswerSelector.getEnergyGuessQuestion();
                break;
            case COMPARISON:
                question = questionAnswerSelector.getComparisonQuestion();
                break;
            case OPEN:
                question = questionAnswerSelector.getOpenQuestion();
                break;
            default:
        }
        Question saved = questionRepository.save(question);
        return ResponseEntity.ok(saved);
    }

    private static boolean isNullOrEmpty(List<String> activityList) {
        return (activityList == null || activityList.size() == 0);
    }
}
