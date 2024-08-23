package game;

import java.awt.*;

/**
 * Represents a rock object in the game.
 * Rock objects are GameObjects with fixed properties.
 */
public class Rock extends GameObject {
    /**
     * Constructs a new Rock object with random position and default properties.
     */
    public Rock() {
        this.x = (int) (Math.random() * 700);
        this.y = (int) (Math.random() * 400 + 400);
        this.width = 71;
        this.height = 71;
        this.flag = false;
        this.m = 70;
        this.count = 0;
        this.type = 2;
        this.img = Toolkit.getDefaultToolkit().getImage("images/rock1.png");
    }
}
