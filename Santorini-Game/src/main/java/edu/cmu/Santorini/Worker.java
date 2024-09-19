package edu.cmu.Santorini;

public class Worker {

    private String workerName; 
    private Position position;

    /**
     * Construtor that creates a worker object and set workerName
     * @param name set worker name
     * @param startingX the initial x position of the worker
     * @param startingY the initial y position of the worker
     */
    public Worker(String name, int startingX, int startingY){
        this.workerName = name;
        try{
            this.position = new Position(startingX,startingY);
        }catch(Exception e){

        }
        
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
    
}

