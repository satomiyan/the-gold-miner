package game;

/**
 * Main class to start the Gold Miner game.
 */
public class GameMain {
    /**
     * Entry point to start the Gold Miner game.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();// Launch the game.
    }
}
