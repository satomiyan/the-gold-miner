package game;


import game.Background.Bg;
import game.GameObject.*;
import game.Line.Line;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the main window of the game.
 */
public class GameWin extends JFrame implements GameWinInterface {
    public static int gameState;// Represents the current state of the game.
    public List<GameObject> objectList = new ArrayList<>();// Stores the objects in the game.
    Bg bg = new Bg();//Background of the game.
    Line line = new Line(this);// Represents the line used in the game.

    /**
     * Generates and places gold and rock objects randomly on the game window.
     * Checks for intersections with existing objects before adding them to the list.
     */ {
        boolean isPlace = true;
        // Generate and place gold objects randomly.
        for (int i = 0; i < 10; i++) {
            double randomNumber = Math.random();
            Gold gold;
            if (randomNumber < 0.6) {
                gold = new GoldMini();
            } else if (randomNumber < 0.9) {
                gold = new Gold();
            } else {
                gold = new GoldPlus();
            }
            // Check if the gold intersects with other objects before adding it to the list.
            for (GameObject obj : objectList) {
                if (gold.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                }
            }
            if (isPlace) {
                objectList.add(gold);
            } else {
                isPlace = true;
                i--;
            }
        }
        // Generate and place rock objects randomly.
        for (int i = 0; i < 3; i++) {
            Rock rock = new Rock();
            // Check if the rock intersects with other objects before adding it to the list.
            for (GameObject obj : objectList) {
                if (rock.getRec().intersects(obj.getRec())) {
                    isPlace = false;
                }
            }
            if (isPlace) {
                objectList.add(rock);
            } else {
                isPlace = true;
                i--;
            }
        }
    }

    Image offScreenImage;

    /**
     * Launches the game window.
     */
    @Override
    public void launch() {
        // Set up the game window.
        this.setVisible(true);
        this.setSize(768, 1000);
        this.setTitle("The Gold Miner");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Add mouse listener for user interaction.
        addMouseListener(new MouseAdapter() {
            /**
             * {@inheritDoc}
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // Perform actions based on the current game state and mouse clicks.
                switch (gameState) {
                    case 0:
                        if (e.getButton() == 3) {
                            // Right mouse click starts the game.
                            gameState = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 1:
                        if (e.getButton() == 1 && line.state == 0) {
                            // Left mouse click sets the line state.
                            line.state = 1;
                        }
                        if (e.getButton() == 3 && line.state == 3 && Bg.getWaterNum() > 0) {
                            // Right mouse click triggers water usage.
                            Bg.waterFlag = true;
                            Bg.waterNum--;
                        }
                        break;
                    case 2:
                        if (e.getButton() == 1) {
                            bg.setShop(true);
                        }
                        if (e.getButton() == 3) {
                            // Right mouse click resumes the game.
                            gameState = 1;
                            bg.startTime = System.currentTimeMillis();
                        }
                        break;
                    case 3:

                    case 4:
                        if (e.getButton() == 1) {
                            // Left mouse click restarts the game.
                            gameState = 0;
                            bg.reGame();
                            line.reGame();
                        }
                        break;
                    default:
                }
            }
        });

        // Main game loop.
        while (true) {
            repaint();
            nextLevel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Progresses to the next level of the game.
     */
    @Override
    public void nextLevel() {
        // Check if conditions are met to proceed to the next level.
        if (bg.gameTime() && gameState == 1) {
            if (Bg.count > bg.getGoal()) {
                if (Bg.getLevel() == 5) {
                    gameState = 4;
                } else {
                    gameState = 2;
                    Bg.level++;
                    Bg.goal += Bg.getLevel() * 5;
                }
            } else {
                gameState = 3;
            }
            // Close the current window and launch a new game window.
            dispose();
            GameWin gameWin = new GameWin();
            gameWin.launch();
        }
    }

    /**
     * Paints the game window.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
    public void paint(Graphics g) {
        // Create an off-screen image buffer to render the graphics.
        offScreenImage = this.createImage(768, 1000);
        // Get the Graphics object of the off-screen image.
        Graphics graphics = offScreenImage.getGraphics();
        bg.paintSelf(graphics);
        // Render game objects and line if the game state is in progress.
        if (gameState == 1) {
            // Render each game object.
            for (GameObject obj : objectList) {
                obj.paintSelf(graphics);
            }
            line.paintSelf(graphics);
        }
        // Draw the off-screen image onto the main screen.
        g.drawImage(offScreenImage, 0, 0, null);
    }

}
