package edu.cmu.Santorini;
import java.util.List;
import java.util.ArrayList;


public class Game {

    private boolean gameStatus;
    private Map gameMap;
    private Player playerA;
    private Player playerB;
    private Player currentPlayer;
    

    /*
     * Create a new Santorini game instance and set gameStatus to true
     * Also initialize the player, worker, map objects
     */
    public Game(String nameA, String nameB) {
        boolean gameStatus = true;
        gameMap = new Map();
        playerA = new Player(nameA);
        playerB = new Player(nameB);

    }

    /*
     * The method toggles between the current player to the other player
     * If current player is playerA, it switches to playerB and vice versa.
     */
    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    /*
     * This method switches the turn between 2 players
     */
    public void togglePlayerStatus(){
        currentPlayer = (currentPlayer == playerA) ? playerB : playerA; 
    }
    
}
