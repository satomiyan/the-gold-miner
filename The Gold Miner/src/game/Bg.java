package game;

import java.awt.*;

/**
 * Background class representing the background of the game.
 */
public class Bg {
    static int level = 1;// Current level of the game
    static int goal = level * 5;// Goal points to reach next level
    static int count = 0;// Current points earned in the game
    static int waterNum = 3;// Number of water resources available
    static boolean waterFlag = false;// Flag indicating if water is available
    long startTime;
    long endTime;

    int price = 5;// Price of water resource
    boolean shop = false;// Flag indicating if the player is in the shop
    Image land = Toolkit.getDefaultToolkit().getImage("images/land.jpg");
    Image sky = Toolkit.getDefaultToolkit().getImage("images/sky.jpg");
    Image worker = Toolkit.getDefaultToolkit().getImage("images/worker.png");
    Image water = Toolkit.getDefaultToolkit().getImage("images/water.png");

    /**
     * Paints the background elements on the graphics object.
     *
     * @param g The graphics object to paint on.
     */
    void paintSelf(Graphics g) {
        g.drawImage(land, 0, 200, null);
        g.drawImage(sky, 0, 0, null);
        switch (GameWin.gameState) {
            case 0://start mode
                drawWord(g, 80, Color.green, "Start!", 200, 400);// Display start message
                break;
            case 1://game mode
                g.drawImage(worker, 300, 50, null);// Draw worker character
                drawWord(g, 30, Color.BLACK, "Points: " + count, 30, 150);// Display current points
                g.drawImage(water, 450, 40, null);// Draw water resource
                drawWord(g, 30, Color.BLACK, "* " + waterNum, 510, 70);// Display water resource count
                drawWord(g, 20, Color.BLACK, "Level: " + level, 30, 60);// Display current level
                drawWord(g, 30, Color.BLACK, "Goal: " + goal, 30, 110);// Display goal points
                endTime = System.currentTimeMillis();
                long time = 20 - (endTime - startTime) / 1000;// Calculate remaining time
                drawWord(g, 30, Color.BLACK, "Time: " + (time > 0 ? time : 0), 520, 150);// Display remaining time
                break;
            case 2://shop mode
                g.drawImage(water, 300, 400, null);
                drawWord(g, 30, Color.BLACK, "Price: " + price, 300, 500);
                drawWord(g, 30, Color.BLACK, "Buy water?", 300, 550);
                if(shop){
                    count -= price;// Deduct price from points
                    waterNum++;
                    shop = false;// Exit shop mode
                    GameWin.gameState = 1;// Switch to game mode
                    startTime = System.currentTimeMillis();
                }
                break;
            case 3://fail mode
                drawWord(g, 80, Color.cyan, "Game Over!", 250, 350);// Display game over message
                drawWord(g, 80, Color.cyan, "Points: " + count, 200, 450);// Display final points
                break;
            case 4://win mode
                drawWord(g, 80, Color.red, "Win!", 250, 350);
                drawWord(g, 80, Color.red, "Points: " + count, 200, 450);
                break;
            default:
        }
    }

    /**
     * Checks if the game time has elapsed.
     *
     * @return True if the game time has elapsed, false otherwise.
     */
    boolean gameTime() {
        long time = (endTime - startTime) / 1000;
        if (time > 20) {
            return true;
        }
        return false;
    }

    /**
     * Resets the game state for a new game.
     */
    void reGame() {
        level = 1;
        goal = level * 5;
        count = 0;
        waterNum = 3;
        waterFlag = false;
    }

    /**
     * Draws a word on the graphics object with specified size, color, and position.
     *
     * @param g     The graphics object to draw on.
     * @param size  The font size of the word.
     * @param color The color of the word.
     * @param str   The word to be drawn.
     * @param x     The x-coordinate of the word.
     * @param y     The y-coordinate of the word.
     */
    public static void drawWord(Graphics g, int size, Color color, String str, int x, int y) {
        g.setColor(color);
        g.setFont(new Font("Times New Roman", Font.BOLD, size));
        g.drawString(str, x, y);
    }
}
