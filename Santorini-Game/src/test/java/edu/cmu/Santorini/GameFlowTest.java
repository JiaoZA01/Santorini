package edu.cmu.Santorini;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class GameFlowTest {
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final int SIX = 6;
    private static final int SEVEN = 7;
    private static final int SPECIAL = -1;
    
    @Test
    public void testGame(){
        
        Game newGame = new Game("JZA","ZYC");

        //Try create worker with an illegal player name, should return false
        assertFalse(newGame.createWorker("LYS", "SB", "DSB"));

        //Now create worker with legal player names
        assertTrue(newGame.createWorker("JZA", "NiuMa1", "NiuMa2"));
        assertTrue(newGame.createWorker("ZYC", "NiuMa3", "NiuMa4"));

        //Try use JZA to place NiuMa3, should return false
        assertFalse(newGame.placeWorker("JZA", "NiuMa3", 1, 1));

        //Now place the workers
        assertTrue(newGame.placeWorker("JZA", "NiuMa1", 1, 1));

        //Place the next worker in occupied grid, should return false
        assertFalse(newGame.placeWorker("JZA", "NiuMa2", 1, 1));

        //Place the rest of the workers
        assertTrue(newGame.placeWorker("JZA", "NiuMa2", FOUR, TWO));
        assertTrue(newGame.placeWorker("ZYC", "NiuMa3", THREE, THREE));
        assertTrue(newGame.placeWorker("ZYC", "NiuMa4", ZERO, ZERO));

        /*
            Board Representation: (A,B,C,D represents NiuMa1-4)

            +----+----+----+----+----+
            |D0  | 0  | 0  | 0  | 0  |  // Row 0
            +----+----+----+----+----+
            | 0  |A0  | 0  | 0  | 0  |  // Row 1 
            +----+----+----+----+----+
            | 0  | 0  | 0  | 0  | 0  |  // Row 2 
            +----+----+----+----+----+
            | 0  | 0  | 0  |C0  | 0  |  // Row 3
            +----+----+----+----+----+
            | 0  | 0  |B0  | 0  | 0  |  // Row 4
            +----+----+----+----+----+
            */

        //Now player1 is the current player, but we'll start with a builder that does not belong to player1
        assertFalse(newGame.moveAndBuild("NiuMa3", 0, 1));

        //Now we build 
        assertTrue(newGame.moveAndBuild("NiuMa1", SIX, TWO));
        assertTrue(newGame.getGridLevel(TWO, ONE) == ONE);

        /*
            Board Representation: (A,B,C,D represents NiuMa1-4)

            +----+----+----+----+----+
            |D0  | 0  | 0  | 0  | 0  |  // Row 0
            +----+----+----+----+----+
            | 0  | 0  | 1  | 0  | 0  |  // Row 1 
            +----+----+----+----+----+
            | 0  |A0  | 0  | 0  | 0  |  // Row 2 
            +----+----+----+----+----+
            | 0  | 0  | 0  |C0  | 0  |  // Row 3
            +----+----+----+----+----+
            | 0  | 0  |B0  | 0  | 0  |  // Row 4
            +----+----+----+----+----+
            */

        //Now the player should have been switched, let's verify that by calling NiuMa1 to build again
        assertFalse(newGame.moveAndBuild("NiuMa1", SIX, TWO));
        assertTrue(newGame.getGridLevel(TWO, ONE) == ONE);

        //Now move NiuMa4 to build on where NiuMa1 has built on
        assertTrue(newGame.moveAndBuild("NiuMa4", SEVEN, FOUR));
        assertTrue(newGame.getGridLevel(TWO, ONE) == TWO);

        /*
            Board Representation: (A,B,C,D represents NiuMa1-4)

            +----+----+----+----+----+
            | 0  | 0  | 0  | 0  | 0  |  // Row 0
            +----+----+----+----+----+
            | 0  |D0  | 2  | 0  | 0  |  // Row 1 
            +----+----+----+----+----+
            | 0  |A0  | 0  | 0  | 0  |  // Row 2 
            +----+----+----+----+----+
            | 0  | 0  | 0  |C0  | 0  |  // Row 3
            +----+----+----+----+----+
            | 0  | 0  |B0  | 0  | 0  |  // Row 4
            +----+----+----+----+----+
            */
        
        // Then let's make sure A and D are actually where they are supposed to be in the map
        // We do that by moving NiuMa1 up, if it can't, it means NiuMa4 is indeed above it
        assertFalse(newGame.moveAndBuild("NiuMa1", ONE, FOUR));
        assertTrue(newGame.getGridLevel(TWO, ONE) == TWO);

        // Let's also make sure that the tower is where it supposed to be and level 2 by 
        // trying to move NiuMa1 onto it, which it should fail because of level difference of 2
        assertFalse(newGame.moveAndBuild("NiuMa1", TWO, FOUR));
        assertTrue(newGame.getGridLevel(TWO, ONE) == TWO);

        //Now let's move and build on the 2-level tower using NiuMa1
        assertTrue(newGame.moveAndBuild("NiuMa1", FOUR, ONE));
        assertTrue(newGame.getGridLevel(TWO, ONE) == THREE);
          /*
            Board Representation: (A,B,C,D represents NiuMa1-4)

            +----+----+----+----+----+
            | 0  | 0  | 0  | 0  | 0  |  // Row 0
            +----+----+----+----+----+
            | 0  |D0  | 3  | 0  | 0  |  // Row 1 
            +----+----+----+----+----+
            | 0  | 0  |A0  | 0  | 0  |  // Row 2 
            +----+----+----+----+----+
            | 0  | 0  | 0  |C0  | 0  |  // Row 3
            +----+----+----+----+----+
            | 0  | 0  |B0  | 0  | 0  |  // Row 4
            +----+----+----+----+----+
            */
        
        //Now the tower is three level high, but before completing the tower, we should make sure
        //worker cannot complete tower if another worker stand on it
        //so let's build more towers
        assertTrue(newGame.moveAndBuild("NiuMa4", ONE, SIX));

            /*
                Board Representation: (A,B,C,D represents NiuMa1-4)

                +----+----+----+----+----+
                | 0  |D0  | 0  | 0  | 0  |  // Row 0
                +----+----+----+----+----+
                | 0  | 1  | 3  | 0  | 0  |  // Row 1 
                +----+----+----+----+----+
                | 0  | 0  |A0  | 0  | 0  |  // Row 2 
                +----+----+----+----+----+
                | 0  | 0  | 0  |C0  | 0  |  // Row 3
                +----+----+----+----+----+
                | 0  | 0  |B0  | 0  | 0  |  // Row 4
                +----+----+----+----+----+
                */
        
        assertTrue(newGame.moveAndBuild("NiuMa1", ZERO, TWO));
            
        /*
                Board Representation: (A,B,C,D represents NiuMa1-4)

                +----+----+----+----+----+
                | 0  |D0  | 1  | 0  | 0  |  // Row 0
                +----+----+----+----+----+
                | 0  |A1  | 3  | 0  | 0  |  // Row 1 
                +----+----+----+----+----+
                | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                +----+----+----+----+----+
                | 0  | 0  | 0  |C0  | 0  |  // Row 3
                +----+----+----+----+----+
                | 0  | 0  |B0  | 0  | 0  |  // Row 4
                +----+----+----+----+----+
                */
        
        //Strictly verify that all the workers are in positions as given in the graph
        //Also verify that the height of the towers are correct
        int[] loc = new int[]{ONE,ZERO};
        assertTrue(Arrays.equals(newGame.getWorkerLocation("NiuMa4"),loc));
        loc = new int[]{ONE,ONE};
        assertTrue(Arrays.equals(newGame.getWorkerLocation("NiuMa1"),loc));
        assertTrue(newGame.getGridLevel(TWO, ONE) == THREE);
        assertTrue(newGame.getGridLevel(TWO, ZERO) == ONE);
        assertTrue(newGame.getGridLevel(ONE, ONE) == ONE);
        assertTrue(newGame.getGameStatus() == true);

         //Make sure NiuMa4 can't build on A1 while A in place
        assertFalse(newGame.moveAndBuild("NiuMa4", THREE, SEVEN));

        //Verify that after a successful move and fail build, the worker will get back to its original place
        loc = new int[]{ONE,ZERO};
        assertTrue(Arrays.equals(newGame.getWorkerLocation("NiuMa4"),loc));

        //Keep building
        assertTrue(newGame.moveAndBuild("NiuMa4", THREE, SIX));

        /*
                Board Representation: (A,B,C,D represents NiuMa1-4)

                +----+----+----+----+----+
                |D0  | 0  | 1  | 0  | 0  |  // Row 0
                +----+----+----+----+----+
                | 1  |A1  | 3  | 0  | 0  |  // Row 1 
                +----+----+----+----+----+
                | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                +----+----+----+----+----+
                | 0  | 0  | 0  |C0  | 0  |  // Row 3
                +----+----+----+----+----+
                | 0  | 0  |B0  | 0  | 0  |  // Row 4
                +----+----+----+----+----+
                */
        
        //Keep building
        assertTrue(newGame.moveAndBuild("NiuMa1", TWO, FIVE));

            /*
                    Board Representation: (A,B,C,D represents NiuMa1-4)

                    +----+----+----+----+----+
                    |D0  | 0  |A1  | 0  | 0  |  // Row 0
                    +----+----+----+----+----+
                    | 1  | 2  | 3  | 0  | 0  |  // Row 1 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  |C0  | 0  |  // Row 3
                    +----+----+----+----+----+
                    | 0  | 0  |B0  | 0  | 0  |  // Row 4
                    +----+----+----+----+----+
                    */
        
        assertTrue(newGame.moveAndBuild("NiuMa4", FOUR, FIVE));

             /*
                    Board Representation: (A,B,C,D represents NiuMa1-4)

                    +----+----+----+----+----+
                    | 0  |D0  |A1  | 0  | 0  |  // Row 0
                    +----+----+----+----+----+
                    | 2  | 2  | 3  | 0  | 0  |  // Row 1 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  |C0  | 0  |  // Row 3
                    +----+----+----+----+----+
                    | 0  | 0  |B0  | 0  | 0  |  // Row 4
                    +----+----+----+----+----+
                    */
        
        assertTrue(newGame.moveAndBuild("NiuMa1", FIVE, THREE));

         /*
                    Board Representation: (A,B,C,D represents NiuMa1-4)

                    +----+----+----+----+----+
                    | 0  |D0  | 1  | 0  | 0  |  // Row 0
                    +----+----+----+----+----+
                    | 3  |A2  | 3  | 0  | 0  |  // Row 1 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  |C0  | 0  |  // Row 3
                    +----+----+----+----+----+
                    | 0  | 0  |B0  | 0  | 0  |  // Row 4
                    +----+----+----+----+----+
                    */

        //Then D will prevent A from climbing up and add dome to the grid at (0,1)
        assertTrue(newGame.moveAndBuild("NiuMa4", THREE, SIX));
        
        /*
                    Board Representation: (A,B,C,D represents NiuMa1-4)
                                           -1 indicates the tower has dome

                    +----+----+----+----+----+
                    | 0  |D0  | 1  | 0  | 0  |  // Row 0
                    +----+----+----+----+----+
                    |-1  |A2  | 3  | 0  | 0  |  // Row 1 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  | 0  | 0  |  // Row 2 
                    +----+----+----+----+----+
                    | 0  | 0  | 0  |C0  | 0  |  // Row 3
                    +----+----+----+----+----+
                    | 0  | 0  |B0  | 0  | 0  |  // Row 4
                    +----+----+----+----+----+
                    */
        
        //A will try climb on the tower with dome but fail
        assertFalse(newGame.moveAndBuild("NiuMa1", THREE, SIX));

        //But it could also move to the right and finish the game
        assertTrue(newGame.moveAndBuild("NiuMa1", FOUR, SIX));

        //Now the gameStatus = 4 and player B can not make any moves
        assertFalse(newGame.moveAndBuild("NiuMa1", THREE, FOUR));
    }   
}
