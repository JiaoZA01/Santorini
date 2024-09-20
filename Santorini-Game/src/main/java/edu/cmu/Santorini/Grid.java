package edu.cmu.Santorini;

public class Grid {
    private boolean hasDome;
    private boolean isOccupied;
    private int level;
    private Position position;
    private static final int MAX_LEVEL = 3;

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
        hasDome = false;
        level = 0;
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
     * Returns the number of level of blocks for the selected block
     */
    public int getLevel(){
        return level;
    }

    /*
     * After building the tower, it modifies the level attribute of the grid
     */
    public boolean addLevel(){
        if (!isOccupied && !hasDome && level < MAX_LEVEL){
            level++;
            return true;
        }
        return false;
    }

    /*
     * Indicates the state of the grid
     * If has dome, then the worker cannot move onto the selected grid
     */
    public boolean hasDome(){
        return hasDome;
    }
    
    /*
     * 
     */
    public boolean setDome(){
        if (this.getLevel() != MAX_LEVEL || this.hasDome()){
            return false;
        }
        this.hasDome = true;
        return true;
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
