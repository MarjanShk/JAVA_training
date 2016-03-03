package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.firstProgram.Equation;

/**
 * Created by Admin on 03.03.2016.
 */
public class EquationTests {
    Equation e0 = new Equation(1, 1, 1);
    Equation e1 = new Equation(1, 2, 1);
    Equation e2 = new Equation(1, 5, 6);

    @Test
    public void testEquation0() {
        Assert.assertEquals(e0.returnN(), 0);
    }

    @Test
    public void testEquation1() {
        Assert.assertEquals(e1.returnN(), 1);
    }

    @Test
    public void testEquation2() {
        Assert.assertEquals(e2.returnN(), 2);
    }
}
