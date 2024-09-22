package edu.cmu.Santorini;
import java.util.List;
import java.util.ArrayList;


public class Game {

    private boolean gameStatus;
    private Map gameMap;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Worker cowHorseA;
    private Worker cowHorseB;
    private Worker cowHorseC;
    private Worker cowHorseD;
    private static final int RESET = 7;

    /*
     * Create a new Santorini game instance and set gameStatus to true
     * Also initialize the player, worker, map objects
     */
    public Game(String nameA, String nameB) {
        gameStatus = true;
        gameMap = new Map();
        player1 = new Player(nameA);
        player2 = new Player(nameB);
        currentPlayer = player1;
    }

    //Default Constructor
    public Game(){

    }

    public boolean getGameStatus(){
        return gameStatus;
    }

    //For testing purposes
    public int[] getWorkerLocation(String workerName){
        return getWorker(workerName).getPosition().getPosition();
    }

    //For testing purposes
    public int getGridLevel(int x, int y){
        return gameMap.getGrid(x, y).getLevel();
    }

    /**
     * Input worker name in string, return the worker object
     * @param workerName
     * @return Worker object with the given name
     */ 
    public Worker getWorker(String workerName){
        if (workerName == cowHorseA.getName()){
            return cowHorseA;
        }else if (workerName == cowHorseB.getName()){
            return cowHorseB;
        }else if (workerName == cowHorseC.getName()){
            return cowHorseC;
        }else if (workerName == cowHorseD.getName()){
            return cowHorseD;
        }else{
            return null;
        }
    }

     /**
     * Input player name in string, return the player object
     * @param playerName
     * @return Player object with the given name
     */ 
    public Player getPlayer(String playerName){
        if (playerName == player1.getPlayerName()){
            return player1;
        }else if(playerName == player2.getPlayerName()){
            return player2;
        }
        return null;
    }
    /**
     * The method create and add workers to the player name provided
     * Does not allow name duplicate for workers that belong to a single player
     * @param playerName
     * @param workerA name of firt worker
     * @param workerB name of second worker
     * @return A boolean indicating if the create is successful
     */

    public boolean createWorker(String playerName, String workerA, String workerB){
        if (workerA == workerB){
            return false;
        }
        int player = (playerName == player1.getPlayerName()) ? 1 : ((playerName == player2.getPlayerName()) ? 2 : 0);
        switch(player){
            case 1:
                cowHorseA = new Worker(workerA);
                cowHorseB = new Worker(workerB);
                player1.addWorker(cowHorseA, cowHorseB);
                break;
            case 2:
                cowHorseC = new Worker(workerA);
                cowHorseD = new Worker(workerB);
                player2.addWorker(cowHorseC, cowHorseD);
                break;
            default:
                return false;    
        }
        return true;
    }

    /**
     * Helps to place the worker at the start of the game. alter the game grid occupancy status
     * @param playerName
     * @param workerName
     * @param posX
     * @param posY
     * @return a boolean that indicates if the placement is successful
     */
    public boolean placeWorker(String playerName, String workerName, int posX, int posY){
        if (getWorker(workerName) == null || !getPlayer(playerName).hasWorker(getWorker(workerName))){
            return false;
        }
        try{
            Position position = new Position(posX,posY);
            if(!getWorker(workerName).setPosition(position, gameMap)){
                return false;
            }
            gameMap.getGrid(position).toggleOccupancy();
            return true;
        }catch (Exception e){
            return false;
        }
        
    }

    /**
     * This method moves and build using the given worker
     * It will not execute if the worker is not of the player's belonging
     * After move and build, it will toggle the current player
     * It will also evaluate if the tower is complete, if so, it will terminate the game
     * @param workerName
     * @param moveDirection
     * @param buildDirection
     * @return A boolean suggesting if the move and build is successful
     */
    public boolean moveAndBuild(String workerName, int moveDirection, int buildDirection){
        if (gameStatus == false){
            return false;
        }
        if (!currentPlayer.moveWorker(moveDirection, getWorker(workerName) , gameMap)){
            return false;
        }
        if (!currentPlayer.buildTower(buildDirection, getWorker(workerName), gameMap)){
            currentPlayer.moveWorker(RESET - moveDirection, getWorker(workerName) , gameMap);
            return false;
        }
        if (gameMap.getGrid(getWorker(workerName).getPosition()).hasCompleteTower()){
            gameOver();
        }
        togglePlayerStatus();
        return true;
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
        currentPlayer = (currentPlayer == player1) ? player2 : player1; 
    }
    
    /**
     * Stop the game flow
     */
    public void gameOver(){
        gameStatus = false;
    }
}
