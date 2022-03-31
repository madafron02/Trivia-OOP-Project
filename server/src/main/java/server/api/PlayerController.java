package server.api;

import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.PlayerRepository;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
    PlayerRepository repo;

    public PlayerController(PlayerRepository repo) {
        this.repo = repo;
    }

    @GetMapping(path = {"", "/"})
    public List<Player> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable long id){
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        Player saved = repo.save(player);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/best")
    public ResponseEntity<Player> getBest(){
        List<Player> players = repo.findAll();
        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        return ResponseEntity.ok(players.get(0));
    }

    /**
     * Sets the new score of a player found by a certain id
     * @param id the id of the player
     * @param score the new score of the player
     * @return bad request if a player with that id doesn't exist,
     * otherwise an 'ok' status
     */
    @PostMapping("/{id}/score_update/{score}")
    public ResponseEntity<Player> updatePlayerScore(@PathVariable("id") long id,
                                                    @PathVariable("score") double score) {
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Player player = repo.findById(id).get();
        player.setPoints((int)score);
        return ResponseEntity.ok(player);
    }

    @GetMapping("/top_ten")
    public List<Player> top(){
        List<Player> players = repo.findAll();
        if(players.size()<10) return players;
        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        return players.subList(0,10);
    }

}
