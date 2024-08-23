package game.GameObject;

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

