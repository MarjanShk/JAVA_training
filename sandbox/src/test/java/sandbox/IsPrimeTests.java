package sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import pack.firstProgram.Prime;
import pack.firstProgram.Prime_while;

/**
 * Created by Admin on 10.03.2016.
 */
public class IsPrimeTests {
    @Test
    public void testPrime(){
        Assert.assertTrue(Prime.isPrime(7));
    }
    @Test
    public void testNoPrime(){
        Assert.assertFalse(Prime.isPrime(6));
    }
    @Test
    public void testIsPrime_while(){
        Assert.assertTrue(Prime_while.isPrime(5));
    }
    @Test
    public void  testNoPrime_while(){
        Assert.assertFalse(Prime_while.isPrime(10));
    }
}
