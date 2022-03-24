package server.api;

import commons.Game;
import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.GameRepository;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    GameRepository repo;
    Game currentGame;
    boolean currentStatus;
    int numberOfReady;
    public GameController(GameRepository repo) {
        currentGame = new Game();
        currentGame.setPlayers(new ArrayList<>());
        currentStatus = false;
        numberOfReady = 0;
        this.repo = repo;
    }
    public void clear(){
        repo.save(currentGame);
        currentGame = new Game();
        currentGame.setPlayers(new ArrayList<>());
        currentStatus = false;
        numberOfReady = 0;
    }
    @GetMapping("/currentGame")
    public ResponseEntity<Game>getCurrentGame(){
        return ResponseEntity.ok(currentGame);
    }
    @GetMapping(path = {"", "/"})
    public List<Game> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable long id){
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        if(game.getId() < 0){
            return ResponseEntity.badRequest().build();
        }
        Game saved = repo.save(game);
        return ResponseEntity.ok(saved);
    }
    @PostMapping("/addToCurrentGame")
    public void addToCurrentGame(@RequestBody Player player){
        List<Player>players = currentGame.getPlayers();
        players.add(player);
        currentGame.setPlayers(players);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable long id){
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Game deleted = repo.findById(id).get();
        repo.delete(deleted);
        return ResponseEntity.ok(deleted);
    }
    @GetMapping("/status")
    public ResponseEntity<Boolean>getCurrentStatus(){
        boolean tmp = currentStatus;
        if(currentStatus == true)numberOfReady++;
        if(numberOfReady == currentGame.getPlayers().size())clear();
        return ResponseEntity.ok(tmp);
    }
    @PostMapping("/status")
    public void setCurrentStatus(@RequestBody boolean s){
        currentStatus = s;
    }
}
