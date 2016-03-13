package pack.firstProgram;

/**
 * Created by Admin on 10.03.2016.
 */
public class Prime_while {
    public static boolean isPrime(int x) {
        int i = 2;
        while (i < x) {
            if (x % i == 0) {
                return false;
            }
            i++;
        }
        return true;
    }
}
