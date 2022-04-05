package server.api;

import commons.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.ActivityRepository;

import java.util.List;

@RestController

@RequestMapping("/api/activity")

public class ActivityController {

    private ActivityService activityService;
    private ActivityRepository repo;


    public ActivityController(ActivityService activityService, ActivityRepository repo) {
        this.activityService = activityService;
        this.repo = repo;
    }

    @GetMapping(path = {"", "/"})
    public List<Activity> getAll() {
        return activityService.listAllActivities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable long id) {
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PostMapping("/post")
    public ResponseEntity<Activity> save(@RequestBody Activity newActivity) {
        return activityService.addActivity(newActivity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable long id) {
        if (id < 0) return ResponseEntity.badRequest().build();
        Activity deleted = repo.findById(id).get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
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
    public ResponseEntity<List<List<Activity>>> getAnswers(@PathVariable Long gameId){
        return ResponseEntity.ok(answers.getGameAnswers(gameId));
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
