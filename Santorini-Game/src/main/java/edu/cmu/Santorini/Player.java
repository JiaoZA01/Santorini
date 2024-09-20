package edu.cmu.Santorini;
import java.util.List;
import java.util.ArrayList;

public class Player {

    private String playerName; // keep an record of player name to differentiate players
    private List<Worker> workers;
    
    public Player(String name){
        this.playerName = name;
        this.workers = new ArrayList<Worker>(2);
    }

    /**
     * Set the userName for the players
     *
     * @param name Provided name by the user
     */
    public void setPlayerName(String name){
        this.playerName = name;
    }

    /*
     * Calling the method retrieves the userName
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * After worker obj is created, they will be added to the player
     * @param workerA 
     * @param workerB
     */
    public void addWorker(Worker workerA, Worker workerB){
        workers = new ArrayList<>(2);
        workers.add(workerA);
        workers.add(workerB);
    }
    
    /**
     * Returns if the user has that specific worker
     * @param worker
     * @return True if worker is in workers list of the player, else false
     */
    public boolean hasWorker(Worker worker){
        return workers.contains(worker);
    }

    /**
     * 
     * @return the worker list
     */
    public List<Worker> getWorkers(){
        return workers;
    }

    

    /**
     * The function checks:
     * 1) whether the worker belongs to the player
     * 2) the vacancy of intended grid (has another player, has dome)
     * 3) the level difference of the intended grid
     * 4）transfromer() method will check if the new location is in bounrds
     * if all good, the workers position will be updated
     * @param direction an int from 0-7 that indicates 8 directions the 
     * @param worker
     * @param map
     * @return True if worker has been successfully moved, otherwise false
     */
    public boolean moveWorker(int direction, Worker worker, Map map){
        if (!this.hasWorker(worker)){
            return false;
        }
        Grid currentGrid = map.getGrid(worker.getPosition());
        Grid selectedGrid = map.getGrid(worker.getPosition().transformer(direction));
        
        if (!selectedGrid.getOccupancy() 
            || selectedGrid.hasDome()
            || (selectedGrid.getLevel() - currentGrid.getLevel() > 1)){
            return false;
        }

        worker.setPosition(selectedGrid.getPosition());

        currentGrid.toggleOccupancy();
        selectedGrid.toggleOccupancy();
        return true;
    }   


}
