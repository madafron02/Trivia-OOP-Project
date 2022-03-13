package server.api;

import commons.Player;
import commons.Round;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.RoundRepository;
import java.util.List;

@RestController
@RequestMapping("/api/rounds")
public class RoundController {
    private final RoundRepository repo;

    public RoundController(RoundRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = {"", "/"})
    public List<Round> getAll() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    public Round getRoundById(@PathVariable long id) {      //returns the object itself,
        if (id < 0 || !repo.existsById(id)) {               //not the responseEntity
            return null;                                    // containing the object in the body
        }
        return repo.findById(id).get();
    }

    @PostMapping(path = {"", "/"})
    public ResponseEntity<String> addRound(@RequestBody Round round) {
        List<Player> playerList = round.getPlayers();
        if (round.getQuestion() == null || round.getQuestion().equals("") ||
                playerList == null || playerList.size() == 0) {
            return ResponseEntity.badRequest().body("The round is invalid");
        }
        repo.save(round);
        return ResponseEntity.ok().body("The round has been saved");
    }
}
