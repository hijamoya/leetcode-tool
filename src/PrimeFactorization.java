import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    void factorization(int n) {
        List<Integer> primes = new ArrayList<>();
        int i = 2;
        int x = n;
        while (i * i <= n) {
            boolean prime = false;
            while (x % i == 0) {
                x = x / i;
                prime = true;
            }
            if (prime) {
                primes.add(i);
            }
            i++;
        }
        if (x > 1) {
            primes.add(x);
        }
    }
}
