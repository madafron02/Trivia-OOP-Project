package server.api;

import commons.Activity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.ActivityRepository;

import java.util.Map;

@RestController

@RequestMapping("/activity")

public class ActivityController {

    ActivityRepository repo;

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable long id) {
        return repo.findById(id).get().getDescription();
    }


    @PostMapping("/activity/post")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity newActivity) {
        if(newActivity.getDescription().isBlank() || newActivity.getDescription().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        Activity saved = repo.save(newActivity);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/delete/activity/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable long id) {
        if (id < 0) return ResponseEntity.badRequest().build();
        Activity deleted = repo.findById(id).get();
        repo.deleteById(id);
        return ResponseEntity.ok(deleted);
    }

}
