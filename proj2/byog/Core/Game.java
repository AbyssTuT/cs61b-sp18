package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;
    public static long SEED = 0;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        //初始化世界
//        World.initialWorld(World.world);
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenRadius(0.1);
//        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.8, "CS61B THE GAME");
        StdDraw.text(0.5, 0.5, "NEW GAME (N)");
        StdDraw.text(0.5, 0.45, "LOAD GAME (L)");
        StdDraw.text(0.5, 0.4, "QUIT GAME (Q)");

        boolean loop = true;
        String input = "";
        while (loop) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char key = StdDraw.nextKeyTyped();
            if (key == 'N' || key == 'n') {
                continue;
            } else if (key > '0' && key < '9') {
                input += String.valueOf(key);
            } else if (key == 'L' || key == 'l') {

            } else if (key == 'Q' || key == 'q') {
                break;
            } else if (key == 'S' || key == 's') {
                loop = false;
            }
        }
        if (input.length() > 0 && loop == false) {
            SEED = Integer.parseInt(input);
            World.startGame();

        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        if ((input.charAt(0) == 'N') && (input.charAt(input.length() - 1) == 'S')) {
            SEED = Integer.parseInt(input.substring(1, input.length() - 1));
            World.startGame();
            TETile[][] finalWorldFrame = World.world;
            return finalWorldFrame;
        } else {
            System.out.println("your game key is not correct");
            return null;
        }

    }
}
