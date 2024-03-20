public class FastPow {
  long mod = (int) 1e9 + 7;

  long myPow(long x, long n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    long k = myPow(x, n / 2);
    return n % 2 == 0 ? (k * k) % mod : (k * k * x) % mod;
  }

  long myPow2(long x, long n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    long k = myPow(x, n / 2);
    long k2 = (k * k) % mod;
    return n % 2 == 0 ? k2 : (k2 * x) % mod;
  }
}
