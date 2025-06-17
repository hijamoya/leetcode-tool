public class Combination {
  int MOD = (int) 1e9 + 7;

  // n的所有組合數
  long[][] comb(int n) {
    long[][] c = new long[n][n];
    c[0][0] = 1;
    for (int i = 1; i < n; ++i) {
      c[i][0] = 1;
      for (int j = 1; j < n; ++j) {
        c[i][j] = (c[i - 1][j - 1] + c[i - 1][j]) % MOD;
      }
    }
    return c;
  }

  // 若是不會超出long的情況
  long comb(int n, int k) {
    if (k == 0 || k == n) {
      return 1;
    }
    if (k == 1) {
      return n;
    }
    return comb(n - 1, k) + comb(n - 1, k - 1);
  }

  // 利用逆元公式，如果上述兩個都會超出內存的話
  long comb2(int n, int k) {
    int MX = 100_000;
    long[] fac = new long[MX];
    long[] invF = new long[MX];
    fac[0] = 1;
    for (int i = 1; i < MX; i++) {
      fac[i] = fac[i - 1] * i % MOD;
    }
    invF[MX - 1] = myPow2(fac[MX - 1], MOD - 2);
    for (int i = MX - 1; i > 0; i--) {
      invF[i - 1] = invF[i] * i % MOD;
    }
    return fac[n] * invF[k] % MOD * invF[n - k] % MOD;
  }

  long myPow2(long x, long n) {
    if (n == 0) {
      return 1;
    }
    if (n == 1) {
      return x;
    }
    long k = myPow2(x, n / 2);
    long k2 = (k * k) % MOD;
    return n % 2 == 0 ? k2 : (k2 * x) % MOD;
  }
}
