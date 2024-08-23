package game.GameObject;

import java.awt.*; /**
 * Represents a smaller version of a Gold object.
 * GoldMini objects inherit properties from Gold and have reduced size and mass.
 */
public class GoldMini extends Gold {
    /**
     * Constructs a new GoldMini object with modified properties.
     */
    public GoldMini() {
        this.width = 36;
        this.height = 36;
        this.m = 15;
        this.count = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold0.gif");
    }
}
