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

    /**
     * initialize the game controller, create a current game instance which contains 0 player
     * @param repo the game repository
     */
    public GameController(GameRepository repo) {
        currentGame = new Game();
        currentGame.setPlayers(new ArrayList<>());
        currentStatus = false;
        numberOfReady = 0;
        this.repo = repo;
    }

    /**
     * save the previous game in the repository
     * create a current game instance which contains 0 player
     */
    public void clear(){
        repo.save(currentGame);
        currentGame = new Game();
        currentGame.setPlayers(new ArrayList<>());
        currentStatus = false;
        numberOfReady = 0;
    }

    /**
     * return the current game status
     * @return the current game
     */
    @GetMapping("/currentGame")
    public ResponseEntity<Game>getCurrentGame(){
        return ResponseEntity.ok(currentGame);
    }

    /**
     * find all games in the repository
     * @return all games in the repository
     */
    @GetMapping(path = {"", "/"})
    public List<Game> getAll() {
        return repo.findAll();
    }

    /**
     * get a game by its id
     * @param id id of the game as the search key
     * @return a game which matches the id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable long id){
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(repo.findById(id).get());
    }

    /**
     * add a game to the repository
     * @param game the game that needs to be saved
     * @return the saved game
     */
    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game){
        if(game.getId() < 0){
            return ResponseEntity.badRequest().build();
        }
        Game saved = repo.save(game);
        return ResponseEntity.ok(saved);
    }

    /**
     * add a player to the current game
     * @param player the player that needs to be added
     */
    @PostMapping("/addToCurrentGame")
    public void addToCurrentGame(@RequestBody Player player){
        List<Player>players = currentGame.getPlayers();
        players.add(player);
        currentGame.setPlayers(players);
    }

    /**
     * delete the game with certain id
     * @param id id of the game
     * @return the deleted game
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Game> deleteGameById(@PathVariable long id){
        if (id < 0 || !repo.existsById(id)) {
            return ResponseEntity.badRequest().build();
        }
        Game deleted = repo.findById(id).get();
        repo.delete(deleted);
        return ResponseEntity.ok(deleted);
    }

    /**
     * check if someone click the start button in the lobby
     * @return true if someone clicks the start button
     */
    @GetMapping("/status")
    public ResponseEntity<Boolean>getCurrentStatus(){
        boolean tmp = currentStatus;
        if(currentStatus == true)numberOfReady++;
        if(numberOfReady == currentGame.getPlayers().size())clear();
        return ResponseEntity.ok(tmp);
    }

    /**
     * set the current status
     * only be called of someone clicks the start button
     * @param s
     */
    @PostMapping("/status")
    public void setCurrentStatus(@RequestBody boolean s){
        currentStatus = s;
    }
}
