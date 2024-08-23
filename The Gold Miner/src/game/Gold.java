package game;

import java.awt.*;


/**
 * Represents a gold object in the game.
 * Gold objects are GameObjects that can have different types and properties.
 */
public class Gold extends GameObject {
    /**
     * Constructs a new Gold object with random position and default properties.
     */
    public Gold() {
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 450 + 400);
        this.width = 52;
        this.height = 52;
        this.flag = false;
        this.m = 30;
        this.count = 4;
        this.type = 1;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold1.gif");
    }
}

/**
 * Represents a smaller version of a Gold object.
 * GoldMini objects inherit properties from Gold and have reduced size and mass.
 */
class GoldMini extends Gold {
    /**
     * Constructs a new GoldMini object with modified properties.
     */
    GoldMini() {
        this.width = 36;
        this.height = 36;
        this.m = 15;
        this.count = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold0.gif");
    }
}

/**
 * Represents a larger version of a Gold object.
 * GoldPlus objects inherit properties from Gold and have increased size and mass.
 */
class GoldPlus extends Gold {

    /**
     * Constructs a new GoldPlus object with random position and modified properties.
     */
    GoldPlus() {
        this.x = (int) (Math.random() * 650);
        this.y = (int) (Math.random() * 400 + 450);
        this.width = 105;
        this.height = 105;
        this.m = 60;
        this.count = 8;
        this.img = Toolkit.getDefaultToolkit().getImage("images/gold2.gif");
    }
}