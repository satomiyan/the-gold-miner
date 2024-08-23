package game.Line;

import game.Background.Bg;
import game.GameObject.GameObject;
import game.GameWin;

import java.awt.*;

/**
 * Represents the line used in the game.
 */
public class Line {
    //X and Y coordinate of the starting point of the line.
    private int x = 380;
    private int y = 200;
    //X and Y coordinate of the ending point of the line.
    private int endX = 600;
    private int endY = 600;
    private double length = 100;// Length of the line.
    private double minLength = 100;// Minimum length the line can reach.
    private double maxLength = 750;// Maximum length the line can reach.
    private double angle = 0;
    private int dir = 1;//Direction of the angle change.
    public int state;// State of the line.
    private GameWin frame;// Reference to the main game window.
    Image hook = Toolkit.getDefaultToolkit().getImage("images/hook.png");

    /**
     * Constructor for the Line class.
     *
     * @param frame The main game window.
     */
    public Line(GameWin frame) {
        this.frame = frame;
    }

    /**
     * Performs logic related to the line.
     */
    void logic() {
        // Check if the line intersects with any object in the game.
        for (GameObject obj : this.frame.objectList) {
            if (endX > obj.getX() && endX < obj.getX() + obj.getWidth() && endY > obj.getY()
                    && endY < obj.getY() + obj.getHeight()) {
                state = 3;// Set state to 3 if intersection occurs.
                obj.setFlag(true);// Set flag of the intersected object to true.
            }
        }
    }

    /**
     * Draws the line on the graphics object.
     *
     * @param g The Graphics object used for drawing.
     */
    public void lines(Graphics g) {
        // Calculate the coordinates of the end point of the line.
        endX = (int) (x + length * Math.cos(angle * Math.PI));
        endY = (int) (y + length * Math.sin(angle * Math.PI));
        // Draw the line.
        g.setColor(Color.red);
        g.drawLine(x - 1, y, endX - 1, endY);
        g.drawLine(x, y, endX, endY);
        g.drawLine(x + 1, y, endX + 1, endY);
        // Draw the hook image at the end point of the line.
        g.drawImage(hook, endX - 36, endY - 2, null);
    }

    /**
     * Paints the line on the graphics object.
     *
     * @param g The Graphics object used for painting.
     */
    public void paintSelf(Graphics g) {
        logic();
        // Switch based on the state of the line.
        switch (state) {
            case 0:
                // Logic for state 0: swinging motion.
                if (angle < 0.1) {
                    dir = 1;
                } else if (angle > 0.9) {
                    dir = -1;
                }
                angle = angle + 0.005 * dir;
                lines(g);
                break;
            case 1:
                // Logic for state 1: extending the line.
                if (length < maxLength) {
                    length += 5;
                    lines(g);
                } else {
                    state = 2;
                }
                break;
            case 2:
                // Logic for state 2: retracting the line.
                if (length > minLength) {
                    length -= 5;
                    lines(g);
                } else {
                    state = 0;
                }
                break;
            case 3:
                // Logic for state 3: handling intersection with objects.
                int m = 1;
                if (length > minLength) {
                    length -= 10;
                    lines(g);
                    // Handle intersection with objects.
                    for (GameObject obj : this.frame.objectList) {
                        if (obj.isFlag()) {
                            m = obj.getM();
                            obj.x = endX - obj.getWidth() / 2;
                            obj.y = endY;
                            // Reset object position if line length is below minimum.
                            if (length <= minLength) {
                                obj.x = -150;
                                obj.y = -150;
                                obj.setFlag(false);
                                Bg.waterFlag = false;
                                Bg.count += obj.getCount();
                                state = 0;
                            }
                            // Handle water usage flag.
                            if (Bg.isWaterFlag()) {
                                if (obj.getType() == 1) {
                                    m = 1;
                                }
                                if (obj.getType() == 2) {
                                    obj.x = -150;
                                    obj.y = -150;
                                    obj.setFlag(false);
                                    Bg.waterFlag = false;
                                    state = 2;

                                }
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(m);// Delay for object interaction.
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;

            default:

        }
    }

    /**
     * Resets the line for a new game.
     */
    public void reGame() {
        angle = 0;
        length = 100;
    }
}
