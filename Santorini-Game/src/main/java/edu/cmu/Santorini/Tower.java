package edu.cmu.Santorini;

public class Tower {
    private int level;
    private boolean hasDome;
    private static final int MAX_LEVEL = 3;

    public Tower() {
        this.level = 0;
        this.hasDome = false;
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
        if (!hasDome && level < MAX_LEVEL){
            level++;
            return true;
        }
        return false;
    }

    /**
     * This examines the tower and return if a dome can be built on this tower
     * @return A boolean indicating the capability to build dome
     */
    public boolean canAddDome(){
        if (!hasDome && level == MAX_LEVEL){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Calling this method returns whether the tower is completed and can no longer been built upon
     * @return A boolean indicating if the tower is completed
     */
    public boolean completeTower() {
        if (level == MAX_LEVEL && hasDome == true) {
            return true;
        }
        return false;
    }
    

    /**
     * This method sets the dome on tower
     * @return A boolean indicating whether setting the dome is successful
     */
    public boolean setDome(){
        if (this.getLevel() != MAX_LEVEL || this.hasDome()){
            return false;
        }
        this.hasDome = true;
        return true;
    }

    /*
     * Indicates the state of the grid
     * If has dome, then the worker cannot move onto the selected grid
     */
    public boolean hasDome(){
        return hasDome;
    }
}