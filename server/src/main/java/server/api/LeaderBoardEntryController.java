package server.api;

import commons.LeaderBoardEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.LeaderBoardEntryRepository;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/LeaderBoardEntry")
public class LeaderBoardEntryController {
    private final LeaderBoardEntryRepository repo;

    public LeaderBoardEntryController(LeaderBoardEntryRepository repo) {
        this.repo = repo;
    }
    @GetMapping(path = { "", "/" })
    public List<LeaderBoardEntry> getAll() {
        return repo.findAll();
    }
    @GetMapping("/best")
    public ResponseEntity<LeaderBoardEntry> getBest() {
        List<LeaderBoardEntry> l = repo.findAll();
        l.sort(new Comparator<LeaderBoardEntry>() {
            @Override
            public int compare(LeaderBoardEntry o1, LeaderBoardEntry o2) {
                return o2.getPoint() - o1.getPoint();
            }
        });
        return ResponseEntity.ok(l.get(0));
    }
    @PostMapping(path = { "", "/post" })
    public ResponseEntity<LeaderBoardEntry> add(@RequestBody LeaderBoardEntry leaderBoardEntry) {

        LeaderBoardEntry saved = repo.save(leaderBoardEntry);
        return ResponseEntity.ok(saved);
    }

}
