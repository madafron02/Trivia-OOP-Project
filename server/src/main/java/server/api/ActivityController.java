package server.api;

import commons.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/activity")

public class ActivityController {

    private ActivityService activityService;


    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping(path = {"", "/"})
    public List<Activity> getAll() {
        return activityService.listAllActivities();
    }

    @PostMapping("/post")
    public ResponseEntity<Activity> save(@RequestBody Activity newActivity) {
        return activityService.addActivity(newActivity);
    }

    /*
    private final ActivityRepository repo;
    private final QuestionAnswerSelector answers;

    public ActivityController(ActivityRepository repo, QuestionAnswerSelector qaSelect) {
        this.repo = repo;
        this.answers = qaSelect;
    }

    @GetMapping(path = {"", "/"})
    public List<Activity> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable String id) {
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @GetMapping("/answers/{gameId}")
    public ResponseEntity<List<Activity>> getAnswers(@PathVariable Long gameId){
        return ResponseEntity.ok(answers.getAnswers(gameId));
    }

    @PostMapping("/post")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity newActivity) {
        if(newActivity.getTitle() == null || newActivity.getTitle().isEmpty()
        || newActivity.getConsumption()<=0 || newActivity.getSource().isEmpty()
        || newActivity.getSource() == null){
            return ResponseEntity.badRequest().build();
        }
        newActivity.setPowerLevel();
        Activity saved = repo.save(newActivity);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable String id) {
        //if (id < 0) return ResponseEntity.badRequest().build();
        Activity deleted = repo.findById(id).get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

     */



}
