package game.GameObject;

import java.awt.*; /**
 * Represents a larger version of a Gold object.
 * GoldPlus objects inherit properties from Gold and have increased size and mass.
 */
public class GoldPlus extends Gold {

    /**
     * Constructs a new GoldPlus object with random position and modified properties.
     */
    public GoldPlus() {
        this.x = (int) (Math.random() * 650);
        this.y = (int) (Math.random() * 400 + 450);
        this.width = 105;
        this.height = 105;
        this.m = 60;
        this.count = 8;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold2.gif");
    }
}
