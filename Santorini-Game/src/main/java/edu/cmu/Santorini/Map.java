package edu.cmu.Santorini;
import java.util.List;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class Map {

    private Grid[][] grid;
    private static final int MAP_SIZE = 5;

    /**
     * The constructor will initialize the map object, including the 5*5 grid
     * Aside from grid, it will also create the scene when UI is incorporated in future development
     */
    public Map(){
        grid = new Grid[MAP_SIZE][MAP_SIZE];
        createMap();
    }
    
    /*
     * the method creates the map by filling all the grid array with a newly created Grid object with attributes
     */
    public void createMap(){
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                grid[i][j] = new Grid(i,j);  
            }
        }
    }

    /**
     * Retrieve the grid given a position object
     * @param position
     * @return The grid object of given position
     */
    public Grid getGrid(Position position){
        int[] pos = position.getPosition();
        return grid[pos[0]][pos[1]];
    }

    public Grid getGrid(int x, int y){
        return grid[x][y];
    }

     /**
     * The method analyzes the position on board and returns whether it is within boundary
     * @param posX 
     * @param posY
     * @return return a boolean suggesting if the location specified in argument is within bound
     */
    public boolean positionInBounds(int posX, int posY){
        if (0 <= posX && posX < MAP_SIZE && 0 <= posY && posY < MAP_SIZE){
            return true;
        }
        return false;
    }
    
}


