package pack.points.method;

/**
 * distance реализован как метод класса Point
 */
public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point b) {
        //AB = √(xb - xa)2 + (yb - ya)2
        return Math.sqrt(Math.pow((b.x - this.x), 2) + Math.pow((b.y - this.y), 2));
    }


}

class TestPoint {
    public static void main(String args[]) {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        System.out.println(p1.distance(p2));

    }
}
