package game;

import game.Background.Bg;

import static org.junit.Assert.*;
/**
 * Unit tests for the GameWin class.
 */

public class GameWinTest {
    /**
     * Sets up the test environment before each test method.
     */
    @org.junit.Before
    public void setUp() throws Exception {
        GameWin gameWin = new GameWin();

        // Check initial game state
        assertEquals(0, GameWin.gameState);
        assertNotNull(gameWin.objectList);
        assertNotNull(gameWin.bg);
        assertNotNull(gameWin.line);
    }
    /**
     * Tests the launch() method of the GameWin class.
     */
    @org.junit.Test
    public void launch() {
        GameWin gameWin = new GameWin();

        // Simulate game launch
        gameWin.launch();

        // Check if game window is visible
        assertTrue(gameWin.isVisible());
    }
    /**
     * Tests the nextLevel() method of the GameWin class.
     */
    @org.junit.Test
    public void nextLevel() {
        GameWin gameWin = new GameWin();
        Bg.level = 1;
        Bg.count = 10;
        Bg.goal = 20;
        GameWin.gameState = 1;

        // Simulate next level
        gameWin.nextLevel();

        // Check if game state is updated correctly
        assertEquals(1, GameWin.gameState);
        assertEquals(20, Bg.goal);
    }
    /**
     * Tests the paint() method of the GameWin class.
     */
    @org.junit.Test
    public void paint() {
        GameWin gameWin = new GameWin();

        // Simulate game launch
        gameWin.launch();

        // Simulate painting the game window
        gameWin.paint(gameWin.getGraphics());

        // Check if painting is successful
        assertNotNull(gameWin.offScreenImage);
    }
}