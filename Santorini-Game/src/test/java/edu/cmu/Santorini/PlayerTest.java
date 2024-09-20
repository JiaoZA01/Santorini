package edu.cmu.Santorini;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class PlayerTest {
    private static final int X = 2;
    private static final int Y = 4;
    private Map map = new Map();
    private Worker workerA = new Worker("ZHZ", X, Y, map);
    private Worker workerB = new Worker("XWH", Y, X, map);
    private Player player1 = new Player("JZA");

    @Test
    public void playerTest(){
        assertTrue(player1.getPlayerName() == "JZA");
    }

    @Test
    public void addWorkerTest(){
        player1.addWorker(workerA, workerB);
        assertTrue(player1.getWorkers().contains(workerA));
        assertTrue(player1.getWorkers().contains(workerB));
        assertTrue(player1.hasWorker(workerA));
        assertTrue(player1.hasWorker(workerB));
    }
}   
