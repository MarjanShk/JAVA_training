package pack.firstProgram;

import pack.points.method.Point;

/**
 * Created by Admin on 26.02.2016.
 */
class TestPoint {
    public static void main(String args[]) {
        Point p1 = new Point(3, 3);
        Point p2 = new Point(7, 7);
        System.out.println(p1.distance(p2));

    }
}
