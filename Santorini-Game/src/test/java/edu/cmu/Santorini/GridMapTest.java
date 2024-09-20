package edu.cmu.Santorini;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class GridMapTest {

    private Map map = new Map();
    private Position position;
    private static final int X = 4;
    private static final int Y = 2;
    private static final int Z = 3;
    private static final int SIX = 6;

    private Worker workerA = new Worker("ZHZ", X, Y, map);
    private Worker workerB = new Worker("XWH", Y, X, map);
    private Player player1 = new Player("JZA");

    /*
     * Indicates the map is generated filled with grids of correct position index
     */
    @Test
    public void mapCreationTest(){
        try{
            position = new Position(X,X);
        }catch(Exception e){

        }
        assertTrue(position.samePosition(map.getGrid(position).getPosition()));
    }

    /*
     * Check whether the occupancy of the grid on the map where the worker is generated has changed
     * after worker is placed on map
     */
    @Test
    public void testGridOccupancy(){
        try{
            position = new Position(Y,X);
        }catch(Exception e) {

        }
        
        assertFalse(map.getGrid(workerA.getPosition()).getOccupancy());
        assertFalse(map.getGrid(position).getOccupancy());

    }

    @Test
    public void testWorkerMovement(){
        
        
        //case1: moveworker should return false if the selected worker to move does not belong to the player
        boolean canMoveWorker = player1.moveWorker(1, workerA, map);
        assertFalse(canMoveWorker);
        player1.addWorker(workerA, workerB);
        boolean canMoveWorkerNow = player1.moveWorker(1, workerA, map);
        assertTrue(canMoveWorkerNow);

        
        //case2: moveworker should return false if the destination is occupied
        try{
            position = new Position(X,Y);
        }catch(Exception e){
        }
        map.getGrid(position).toggleOccupancy();
        assertFalse(player1.moveWorker(SIX, workerA, map));

        map.getGrid(position).toggleOccupancy();
        assertTrue(player1.moveWorker(SIX, workerA, map));

        // verifies that the occupancy state of the previous and current grid changes accordingly 
        assertFalse(map.getGrid(position).getOccupancy());
        position.setPosition(X, 1);
        assertTrue(map.getGrid(position).getOccupancy());

        
        //case3: moveworker should return false if the destination has dome
        // test setDome function when the level is below 3
        assertFalse(map.getGrid(position).setDome());

        // test addLevel function
        for (int i=0; i < Z; i++){
            map.getGrid(position).addLevel();
        }
        assertTrue(map.getGrid(position).setDome());
        
        assertFalse(player1.moveWorker(1, workerA, map));

        int [] correctAPosition = {X, Y};
        assertTrue(Arrays.equals(correctAPosition, workerA.getPosition().getPosition()));
        
        
        //case4: moveworker should return false if the destination is more than 1 level higher worker's current position
        position.setPosition(X,Z);
        map.getGrid(position).addLevel();
        map.getGrid(position).addLevel();
        assertFalse(player1.moveWorker(SIX, workerA, map));


        //case5: moveworker should throw an exception if destination is out of bounds
        assertThrows(Exception.class, () -> {
            player1.moveWorker(X, workerA, map);
        });
    }

}
