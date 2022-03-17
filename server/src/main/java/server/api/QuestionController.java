package server.api;

import commons.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.QuestionRepository;

import java.util.List;

@RestController

@RequestMapping("/api/question")

public class QuestionController {

    private final QuestionRepository repo;

    public QuestionController(QuestionRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = {"/",""})
    public List<Question> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getById(@PathVariable("id") long id) {
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.getById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Question> add(@RequestBody Question question) {

        if (question.getDescription() == null || isNullOrEmpty(question.getAnswers())) {
            return ResponseEntity.badRequest().build();
        }

        Question saved = repo.save(question);
        return ResponseEntity.ok(saved);
    }

    private static boolean isNullOrEmpty(List<String> activityList) {
        return (activityList == null || activityList.size() == 0);
    }
}
