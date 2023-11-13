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
}
