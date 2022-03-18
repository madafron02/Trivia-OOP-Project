package server.api;

import commons.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.ActivityRepository;

import java.util.List;

@RestController

@RequestMapping("/api/activity")

public class ActivityController {

    private final ActivityRepository repo;

    public ActivityController(ActivityRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = {"", "/"})
    public List<Activity> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivity(@PathVariable long id) {
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable long id) {
        return repo.findById(id).get().getTitle();
    }


    @PostMapping("/post")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity newActivity) {
        if(newActivity.getTitle() == null || newActivity.getTitle().isEmpty()
        || newActivity.getConsumption()<=0 || newActivity.getSource().isEmpty()
        || newActivity.getSource() == null){
            return ResponseEntity.badRequest().build();
        }
        Activity saved = repo.save(newActivity);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable long id) {
        if (id < 0) return ResponseEntity.badRequest().build();
        Activity deleted = repo.findById(id).get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

}
