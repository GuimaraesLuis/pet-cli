package Models;
public class Rectangle {
    public int x;
    public int y;
    public int w;
    public int h;

    // Construtor
    public Rectangle(int w, int h,  int x,  int y) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public Rectangle(int w, int h){
        this(w, h, 0, 0);
    }
}
