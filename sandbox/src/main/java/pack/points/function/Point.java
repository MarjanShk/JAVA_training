package pack.points.function;

/**
 * distance реализована как функция
 */
public class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class TestPoint {

    public static double distance(Point a, Point b) {
        //AB = √(xb - xa)2 + (yb - ya)2
        return Math.sqrt(Math.pow((b.x - a.x), 2) + Math.pow((b.y - a.y), 2));
    }

    public static void main(String args[]) {
        Point p1 = new Point(-5, 1);
        Point p2 = new Point(2, -3);
        System.out.println(distance(p1, p2));
    }
}