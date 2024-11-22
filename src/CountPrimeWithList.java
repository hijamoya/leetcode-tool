import java.util.ArrayList;
import java.util.Arrays;

public class CountPrimeWithList {
    // 歐拉篩
    // 算出小於n的所有質數
    int n = 99999;
    boolean[] isPrime = new boolean[n + 1];
    ArrayList<Integer> primes = new ArrayList<>();

    void count() {
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primes.add(i); // i 是质数
            }
            for (int prime : primes) {
                if (i * prime > n) {
                    break;
                }
                isPrime[i * prime] = true; // 标记 i * prime 为合数
                if (i % prime == 0) {
                    break;
                }
            }
        }
    }
}
