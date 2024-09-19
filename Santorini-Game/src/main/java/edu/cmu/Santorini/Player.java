package edu.cmu.Santorini;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private String playerName; // keep an record of player name to differentiate players
    private List<Worker> workers;
    
    public Player(){
        
    }

    /**
     * Set the userName for the players
     *
     * @param name Provided name by the user
     */
    public void setPlayerName(String name){
        playerName = name;
    }

    /*
     * Calling the method retrieves the userName
     */
    public String getPlayerName(String name){
        return playerName;
    }

    /*public void addWorker(String nameWorkerA, String nameWorkerB){
        workers = new ArrayList<>(2);
        workers
    }

    public List<Worker> getWorkers(){
        return workers;
    }

    public String 

    public void moveWorker(Worker workerName){

    }
    
    public void */


}
