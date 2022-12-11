public class StringHash {
  long[] hash;
  long[] pow;
  int p = 31;

  void build(String s) {
    int n = s.length();
    hash = new long[n + 1];
    pow = new long[n + 1];
    hash[0] = 1;
    pow[0] = 1;
    for (int i = 1; i <= n; i++) {
      hash[i] = hash[i - 1] * p + s.charAt(i - 1);
      pow[i] = pow[i - 1] * p;
    }
  }

  long hash(int l, int r) {
    return hash[r] - hash[l] * pow[r - l];
  }
}
