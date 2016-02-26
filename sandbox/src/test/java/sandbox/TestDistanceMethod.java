package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.points.method.Point;

/**
 * Created by Admin on 26.02.2016.
 */
public class TestDistanceMethod {
    @Test
    public void testDistance() {

        Point p = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Assert.assertEquals(p.distance(p2), 1.4142135623730951);

        Point p3 = new Point(3, 3);
        Point p4 = new Point(7, 7);
        Assert.assertEquals(p3.distance(p4), 5.656854249492381);


    }
}
