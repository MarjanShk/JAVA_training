package pack.firstProgram;

/**
 * Created by Admin on 10.03.2016.
 */
public class Prime {
    public static boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
