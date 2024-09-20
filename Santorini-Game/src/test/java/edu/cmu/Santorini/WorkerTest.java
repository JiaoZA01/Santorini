package edu.cmu.Santorini;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class WorkerTest {

    private static final int X = 2;
    private static final int Y = 4;
    private static final int Z = 6;
    private Map map = new Map();
    private Worker workerA = new Worker("ZHZ", X, Y, map);
    private Worker workerB = new Worker("XWH", X, Y, map);
    private Worker workerC = new Worker("XWH", Y, X, map);
    private Position aPosition = workerA.getPosition();
    private Position bPosition = workerB.getPosition();
    private Position cPosition = workerC.getPosition();

    @Test
    public void getCorrectPosition(){
        int[] correctBPosition = new int[]{X, Y};
        boolean samePosition = Arrays.equals(bPosition.getPosition(), correctBPosition);
        assertTrue(samePosition);
    }

    @Test
    public void testSamePosition() {
        // Act
        boolean areSame = aPosition.samePosition(bPosition);

        // Assert
        assertTrue(areSame);
    }

    @Test
    public void testDifferentPosition() {
        // Act
        boolean areSame = aPosition.samePosition(cPosition);

        // Assert
        assertFalse(areSame);
    }

    @Test
    public void testPromixityPosition() {
        boolean samePosition = aPosition.positionInProximity(cPosition);
        boolean notInProximity = aPosition.positionInProximity(cPosition);

        assertFalse(samePosition);
        assertFalse(notInProximity);
    }

}