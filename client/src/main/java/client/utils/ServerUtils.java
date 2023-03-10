/*
 * Copyright 2021 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package client.utils;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.List;

import commons.Game;
import commons.Player;
import commons.Question;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.GenericType;
import org.glassfish.jersey.client.ClientConfig;

import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;


public class ServerUtils {

    private static String SERVER = "";

    /**
     * set the server url by the client's input
     * @param url the input url
     */
    public static boolean setServer(String url){
        SERVER = url;
        try{
            ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get();
        } catch (IllegalArgumentException e2){
            return false;
        } catch (ProcessingException e2){
            return false;
        }
        return true;
    }

    /**
     * save a player in the player repository
     * @param player the player that needs to be saved
     * @return the saved player
     */
    public static Player addPlayer(Player player) {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/player/add") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(player, APPLICATION_JSON), Player.class);
    }

    /**
     * save a game in the game repository (only for single player)
     * @param game the game thqt needs to be saved
     * @return the saved game
     */
    public Game addGame(Game game){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/add") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(game, APPLICATION_JSON), Game.class);
    }
    /**
     * get all players in the repository
     * @return all players in the repository
     */
    public List<Player> getPlayers() {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/player/") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(new GenericType<List<Player>>() {});
    }

    /**
     * require a question according to certain gameId and roundId
     * @param gameId indicates the game
     * @return roundId indicates the round number
     */
    public static Question requireQuestion(long gameId,int roundId) {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/question/getQ/" + gameId + "/" + roundId) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(Question.class);
    }

    /**
     * ask the server to generate the questions for a certain game
     * @param gameId indicate the game
     */
    public void setQuestion(long gameId){
        ClientBuilder.newClient(new ClientConfig()) //
            .target(SERVER).path("api/question/setQ/" + gameId) //
            .request(APPLICATION_JSON) //
            .accept(APPLICATION_JSON) //
            .get();
    }
    /**
     * get the current game (only in mutiplayer mode)
     * @return the current game instance
     */
    public Game getGame() {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/currentGame") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(Game.class);
    }
    /**
     * require a game from the server according to its id
     * @return the current game instance
     */
    public Game requireGame(long id) {
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/"+id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(Game.class);
    }
    /**
     * add a player to the current game (only called in mutiplayer mode)
     * @param player the player that needs to be added
     */
    public Game addPlayerToCurrentGame(Player player) {
        return ClientBuilder.newClient(new ClientConfig()) //
            .target(SERVER).path("api/games/addToCurrentGame") //
            .request(APPLICATION_JSON) //
            .accept(APPLICATION_JSON) //
            .post(Entity.entity(player, APPLICATION_JSON), Game.class);
    }

    /**
     * tell the server the current client click the start button(only called in mutiplayer mode)
     * @param status can only be true
     */
    public void setStatus(Boolean status){
        ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/status") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(status, APPLICATION_JSON), Boolean.class);
    }

    /**
     * ask the server whether its time to travel to the game page(only called in mutiplayer mode)
     * @return if its time to travel to the game page.
     */
    public boolean getStatus(){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/status") //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(Boolean.class);
    }

    /**
     * ask the server if its ready for the next round
     * @param id the game id
     * @return true if it's ready for the next round
     */
    public Boolean readyForNextRound(long id){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/checkStatus/" + id) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .get(Boolean.class);
    }

    /**
     * use the newest player to replace the old player in the current game
     * @return the game contains the newest infomation
     */
    public static Game updatePlayer(long gameId,Player player){
        return ClientBuilder.newClient(new ClientConfig()) //
                .target(SERVER).path("api/games/setPlayer/" + gameId) //
                .request(APPLICATION_JSON) //
                .accept(APPLICATION_JSON) //
                .post(Entity.entity(player, APPLICATION_JSON), Game.class);
    }
}
