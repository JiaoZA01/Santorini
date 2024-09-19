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
                grid[i][j] = new Grid();  
            }
        }
    }


    
}


