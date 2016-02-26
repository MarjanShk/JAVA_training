package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.firstProgram.Square;

/**
 * Created by Admin on 25.02.2016.
 */
public class TestArea {

@Test
    public void testArea(){
    Square s = new Square(6);
    Assert.assertEquals(s.area(), 36.0);
}
}
