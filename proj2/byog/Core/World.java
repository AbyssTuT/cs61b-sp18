package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/11 下午3:46
 */
public class World {
    public static final int WIDTH = Game.WIDTH;
    public static final int HEIGHT = Game.HEIGHT;
    private static final long SEED = Game.SEED;
    private static final Random RANDOM = new Random(SEED);
    private static ArrayList<Room> rooms = new ArrayList<>();
    public static TETile[][] world = new TETile[WIDTH][HEIGHT];
    public static final TERenderer ter = new TERenderer();

    public static void initialWorld(TETile[][] world) {
        ter.initialize(WIDTH, HEIGHT);
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void addSingleRoom(Room room) {
        for (int x = room.position.x - 1; x < room.position.x + room.width + 1; x++) {
            for (int y = room.position.y - 1; y < room.position.y + room.height + 1; y++) {
                world[x][y] = Tileset.WALL;
            }
        }
        for (int x = room.position.x; x < room.position.x + room.width; x++) {
            for (int y = room.position.y; y < room.position.y + room.height; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
        rooms.add(room);
    }

    public static void addRooms(int num) {
        int totalNum = 0;
        while (totalNum != num) {
            int randomXPos = RandomUtils.uniform(RANDOM, 1, WIDTH - Room.MAXROOMLENGTH - 1);
            int randomYPos = RandomUtils.uniform(RANDOM, 1, HEIGHT - Room.MAXROOMLENGTH - 1);
            int randomX = RandomUtils.uniform(RANDOM, 2, Room.MAXROOMLENGTH);
            int randomY = RandomUtils.uniform(RANDOM, 2, Room.MAXROOMLENGTH);
            Room room = new Room(new Position(randomXPos, randomYPos), randomX, randomY);
            if (rooms.size() == 0) {
                addSingleRoom(room);
                totalNum++;
                continue;
            }
            boolean overlap = false;
            for (Room room1 : rooms) {
                if (room.overlap(room1)) {
                    overlap = true;
                    break;
                }
            }
            if (overlap == false) {
                addSingleRoom(room);
                totalNum++;
            }
        }
    }

    public static void rearrangeRooms() {
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                return o1.position.x - o2.position.x;
            }
        });
    }

    public static void addHallway(Room s, Room e) {
        int intersectionY = RandomUtils.uniform(RANDOM, s.position.y, s.position.y + s.height);
        int intersectionX = RandomUtils.uniform(RANDOM, e.position.x, e.position.x + e.width);

        drawHorizontalHallway(s.position.x, intersectionX, intersectionY);
        drawVerticalHallway(e.position.y, intersectionY, intersectionX);
    }

    public static void drawHorizontalHallway(int start, int end, int y) {
        int tmp = 0;
        if (start >= end) {
            tmp = end;
            end = start;
            start = tmp;
        }
        for (int i = start; i <= end; i++) {
            //draw wall
            drawWallHelper(world, i, y - 1);
            drawWallHelper(world, i, y + 1);
            //draw floor
            world[i][y] = Tileset.FLOOR;
        }
    }

    public static void drawVerticalHallway(int start, int end, int x) {
        int tmp = 0;
        if (start >= end) {
            tmp = end;
            end = start;
            start = tmp;
        }
        for (int i = start; i <= end; i++) {
            //draw wall
            drawWallHelper(world, x - 1, i);
            drawWallHelper(world, x + 1, i);
            //draw floor
            world[x][i] = Tileset.FLOOR;
        }
    }

    public static void drawWallHelper(TETile[][] world, int x, int y) {
        if (world[x][y] != Tileset.FLOOR) {
            world[x][y] = Tileset.WALL;
        }
    }

    public static boolean canBeDoor(TETile[][] world, int x, int y) {
        if ((world[x - 1][y] == Tileset.FLOOR && world[x + 1][y] == Tileset.NOTHING) ||
                (world[x + 1][y] == Tileset.FLOOR && world[x - 1][y] == Tileset.NOTHING) ||
                (world[x][y - 1] == Tileset.FLOOR && world[x][y + 1] == Tileset.NOTHING) ||
                (world[x][y + 1] == Tileset.FLOOR && world[x][y - 1] == Tileset.NOTHING)
        ) {
            return true;
        }
        return false;
    }

    public static void drawLockedDoor() {
        while (true) {
            int x = RandomUtils.uniform(RANDOM, 1, WIDTH - 1);
            int y = RandomUtils.uniform(RANDOM, 1, HEIGHT - 1);
            if (world[x][y] == Tileset.WALL && canBeDoor(world, x, y)) {
                world[x][y] = Tileset.LOCKED_DOOR;
                break;
            }
        }
    }

    public static void createWorld() {
        initialWorld(world);

        // create rooms
        int roomNum = RandomUtils.uniform(RANDOM, 15, 20);
        addRooms(roomNum);

        //rearrange rooms according to xPos
        rearrangeRooms();
        //connect rooms with hallway
        for (int i = 0; i < rooms.size() - 1; i++) {
            addHallway(rooms.get(i), rooms.get(i + 1));
        }

        drawLockedDoor();

        ter.renderFrame(world);
    }

//    public static void main(String[] args) {
//        createWorld();
//    }
}
