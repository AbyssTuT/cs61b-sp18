package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 60;

    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        for (int y = p.y; y < p.y + s * 2; y++) {
            int startX = p.x + (int) Math.abs(s - (y - p.y) - 0.5);
            int endX = p.x + (3 * s - 2) - (int) Math.abs(s - (y - p.y) - 0.5);
            for (int x = startX; x <= endX; x++) {
                world[x][y] = Tileset.WALL;
            }

        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        addHexagon(world, new Position(3, 3), 6, Tileset.WALL);
        ter.renderFrame(world);
    }
}

class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
