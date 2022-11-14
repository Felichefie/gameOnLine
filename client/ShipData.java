import java.io.Serializable;

public class ShipData implements Serializable {
    private int x, y;
    private int lives;
    private double angle;
    private String name;

    public ShipData(int x, int y, String name){
        this.x = x;
        this.y = y;
        lives = 3;
        angle = 91.1;
        this.name = name;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public String getName() {
        return name;
    }
}