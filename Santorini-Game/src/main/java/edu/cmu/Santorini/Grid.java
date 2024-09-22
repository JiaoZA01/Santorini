package edu.cmu.Santorini;

public class Grid {
    private Position position;
    private Tower tower;
    private boolean isOccupied;

    /**
     * Constructor that initializes a grid object
     * Set the level, hasDone and isOccupied to false by default
     * @param posX x position of the grid
     * @param posY y position of the grid
     */
    public Grid(int posX, int posY){
        try{
            this.position = new Position(posX, posY);
        }catch(Exception e){
        }
        isOccupied = false;
        tower = new Tower();
    }

    /**
     * Calling this method returns whether the tower is completed and can no longer been built upon
     * @return A boolean indicating if the tower is completed
     */
    public boolean hasCompleteTower() {
        if (this.tower.completeTower()) {
            return true;
        }
        return false;
    }

    /*
     * Returns the occupancy of the grid
     * If false, it means another worker is occupying the gird
     */
    public boolean getOccupancy(){
        return !isOccupied;
    }

    /*
     * Toggles the occupancy state of the grid 
     * If occupied, calling the function switch to unoccupied
     * Called after the worker moves from one grid to anther
     */
    public void toggleOccupancy(){
        isOccupied = !isOccupied;
    }

    /*
     * Returns the number of level of blocks for the selected block, -1 if the tower is completed
     */
    public int getLevel(){
        if (this.tower.completeTower()){
            return -1;
        }
        return this.tower.getLevel();
    }

    /*
     * After building the tower, it modifies the level attribute of the grid
     */
    public boolean addLevel(){
        if (!isOccupied && !hasCompleteTower()){
            this.tower.addLevel();
            return true;
        }
        return false;
    }
    
    /**
     * This method sets the dome on tower
     * @return A boolean indicating whether setting the dome is successful
     */
    public boolean setDome(){
        if (this.tower.canAddDome()){
            this.tower.setDome();
            return true;
        }
        return false;
    }

    /**
     * This method retrieves the position attribute of the obj
     * @return position of the obj
     */
    public Position getPosition(){
        return this.position;
    }

    /**
     * This function allows grid comparison via their position
     * @param grid
     * @return A boolean indicating if the two grids are same
     */
    public boolean sameGrid(Grid grid){
        if (this == grid){
            return true;
        }
        if (this.getPosition() == grid.getPosition()){
            return true;
        }
        return false;
    }
}
