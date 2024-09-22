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
     * @param worker number from 0-7 which indcates the direction of movement as shown below:
     *                  0 1 2
     *                  3   4
     *                  5 6 7
     * @param map 
     * @return True if worker has been successfully moved, otherwise false
     */
    public boolean moveWorker(int direction, Worker worker, Map map){
        if (!this.hasWorker(worker)){
            return false;
        }
        Grid currentGrid = map.getGrid(worker.getPosition());
        Grid selectedGrid = map.getGrid(worker.getPosition().transformer(direction));
        
        if (!worker.canMove(currentGrid, selectedGrid)){
            return false;
        }

        worker.setPosition(selectedGrid.getPosition());

        currentGrid.toggleOccupancy();
        selectedGrid.toggleOccupancy();
        return true;
    }
    
    /**
     * This method allows the player to select its worker and build on a direction relative to the worker's position
     * 1) it will return false if: worker not belong to player, selected grid is occupied or has complete tower
     * 2) it will throw exception if, the direction argument are not 0-7 or the selected grid is out of bound
     * @param direction number from 0-7 which indcates the direction of movement as shown below:
     *                  0 1 2
     *                  3   4
     *                  5 6 7
     * @param worker a worker that belongs to the player
     * @param map the map being played on
     * @return true if build is successful
     */
    public boolean buildTower(int direction, Worker worker, Map map){
        if (!this.hasWorker(worker)){
            return false;
        }
        Grid selectedGrid = map.getGrid(worker.getPosition().transformer(direction));
        if (!worker.buildTower(selectedGrid)){
            return false;
        }
        worker.buildTower(selectedGrid);
        return true;
    }


}
