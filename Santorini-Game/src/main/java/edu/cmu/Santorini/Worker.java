package edu.cmu.Santorini;

public class Worker {

    private String workerName; 
    private Position position;

    /**
     * Construtor that creates a worker object and set workerName
     * the worker will be placed on the map and toggle the occupancy state of the grid
     * @param name set worker name
     * @param startingX the initial x position of the worker
     * @param startingY the initial y position of the worker
     * @param map the map that the worker is added on 
     */
    public Worker(String name, int startingX, int startingY, Map map){
        this.workerName = name;
        try{
            this.position = new Position(startingX,startingY);
        }catch(Exception e){

        }
        if (!map.getGrid(position).getOccupancy()){
            System.err.println("The selected grid has been occupied!");
        }
        map.getGrid(position).toggleOccupancy();
        
    }

    /**
     * 
     * @return name of the worker
     */
    public String getName(){
        return this.workerName;
    }

    /**
     * This method retrieves the position attribute of the obj
     * @return position of the obj
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * Set the position of the worker
     * @param position
     */
    public void setPosition(Position position){
        this.position = position;
    }
    
    /**
     * This method returns if a worker is able to move in a direction
     * @param current current grid
     * @param next next grid
     * @return a boolean indicating if the worker can move in the intended direction
     */
    public boolean canMove(Grid current, Grid next){
        
        if (!next.getOccupancy() 
            || next.hasCompleteTower()
            || (next.getLevel() - current.getLevel() > 1)){
            return false;
        }

        return true;
    }

    /**
     * This build function will build on a selected grid that is not occupied and has no completed tower
     * It automatically build the dome if the tower on the grid has three levels
     * @param grid The inteded grid to build on
     * @return A boolean indicating if the building is successful
     */
    public boolean buildTower(Grid grid){
        if (grid.hasCompleteTower() || !grid.getOccupancy()){
            return false;
        }
        if (grid.setDome()){
            grid.setDome();
        }else{
            grid.addLevel();
        }
        return true;
    }

}

