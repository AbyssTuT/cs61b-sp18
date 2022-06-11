package byog.Core;

/**
 * @author tuhongchang
 * @version 1.0
 * @create 2022/6/11 下午3:46
 */
public class Room {
    Position position;
    int width;
    int height;
    public static final int MAXROOMLENGTH = 10;


    public Room(Position position, int width, int height) {
        this.position = position;
        this.height = height;
        this.width = width;
    }

    public boolean overlap(Room room) {
        if (this.position.x > room.position.x + room.width + 1||
                this.position.x < room.position.x - room.width - 1 ||
                this.position.y > room.position.y + room.height + 1||
                this.position.y < room.position.y - room.height - 1
        ) {
            return false;
        }
        return true;
    }
}
