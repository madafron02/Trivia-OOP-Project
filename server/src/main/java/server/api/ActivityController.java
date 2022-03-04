package server;

import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

@RequestMapping("/activity")

public class ActivityController {

    public static int id=0;
    public String description;
    public Map<Integer, String> mapDescription = new HashMap<Integer, String>();


    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable int id) {
        return mapDescription.get(id);
    }


    @PostMapping("/activity/post")
    public Map<Integer, String>  addActivity(@RequestBody String addDescription) {
        id++;
        mapDescription.put(id,addDescription);
        return mapDescription;
    }

    @PutMapping("replace/activity/{id}")
    public Map<Integer, String>  replaceActivity(@PathVariable int id, @RequestBody String newDescription) {
        if (id < 0) throw new IndexOutOfBoundsException();
        mapDescription.put(id,newDescription);
        return mapDescription;
    }

    @DeleteMapping("/delete/activity/{id}")
    public Map<Integer, String> deleteActivity(@PathVariable int id) {
        if (id < 0) throw new IndexOutOfBoundsException();
        mapDescription.remove(id);
        return mapDescription;
    }

}

