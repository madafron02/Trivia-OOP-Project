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
        if(player.getId()<0 || player.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        Player saved = repo.save(player);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/best")
    public ResponseEntity<Player> getBest(){
        List<Player> players = repo.findAll();
        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        return ResponseEntity.ok(players.get(0));
    }

    @GetMapping("/top_ten")
    public List<Player> top(){
        List<Player> players = repo.findAll();
        if(players.size()<10) return players;

        players.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        return players.subList(0,9);
    }

}
