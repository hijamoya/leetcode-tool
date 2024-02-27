import java.util.Arrays;

public class CountPrime {
    // 埃氏篩
    // 算出小於n的所有質數
    int n = 99999;
    boolean[] primes = new boolean[n + 1];

    void count() {
        Arrays.fill(primes, true);
        primes[1] = false;
        primes[0] = false;
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) {
                continue;
            }
            for (int j = i * i; j <= n; j += i) {
                primes[j] = false;
            }
        }
    }

    boolean isPrime(int k) {
        return primes[k];
    }
}
