package game.GameObject;

import java.awt.*;

/**
 * Represents an object in the game.
 */
public class GameObject {
    public int x;
    public int y;
    int width;
    int height;
    Image img;
    boolean flag = false;// Flag indicating special conditions of the object.
    int m;// Special parameter used for object behavior.
    int count;// Count associated with the object.
    int type;//gold or rock

    /**
     * Paints the object on the graphics object.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    /**
     * Returns the X-coordinate of the object.
     *
     * @return The X-coordinate of the object.
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the Y-coordinate of the object.
     *
     * @return The Y-coordinate of the object.
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of the object.
     *
     * @return The width of the object.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the object.
     *
     * @return The height of the object.
     */
    public int getHeight() {
        return height;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getM() {
        return m;
    }

    public int getCount() {
        return count;
    }

    public int getType() {
        return type;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Returns the bounding rectangle of the object.
     *
     * @return The bounding rectangle of the object.
     */
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
