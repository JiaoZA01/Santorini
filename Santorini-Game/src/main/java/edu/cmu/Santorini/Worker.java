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

    public void setPosition(Position position){
        this.position = position;
    }
    
}

