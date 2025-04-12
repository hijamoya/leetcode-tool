// n!怎麼算，(n-1)!怎麼算
public class Factorial {
    int n = 10000;
    long[] factorial = new long[n + 1];

    void calc() {
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * (long) i;
        }
        // 要計算 (n - 1)! = factorial[n - 1]
    }
}
