// https://leetcode.cn/problems/count-of-integers/description 2719
public class DigitDp2 {
  int mod = (int) 1e9 + 7;
  Long[][] memo;

  public int count(String num1, String num2, int min_sum, int max_sum) {
    // 數位dp
    memo = new Long[30][500];
    long a = dfs(num1, min_sum, max_sum, 0, 0, true, false);
    memo = new Long[30][500];
    long b = dfs(num2, min_sum, max_sum, 0, 0, true, false);
    return (int) (((b - a + (check(num1, min_sum, max_sum) ? 1 : 0)) % mod) + mod) % mod;
  }

  boolean check(String num, int min_sum, int max_sum) {
    int sum = 0;
    for (int i = 0; i < num.length(); i++) {
      sum += (num.charAt(i) - '0');
    }
    return sum >= min_sum && sum <= max_sum;
  }

  long dfs(String num, int min_sum, int max_sum, int i, int sum, boolean isLimit, boolean isNum) {
    if (sum > max_sum) {
      return 0;
    }
    if (i == num.length()) {
      return isNum && sum >= min_sum ? 1 : 0; // isNum 为 true 表示得到了一个合法数字
    }
    if (!isLimit && isNum && memo[i][sum] != null) {
      return memo[i][sum];
    }
    long res = 0;
    if (!isNum) {
      // 可以跳过当前数位
      res = dfs(num, min_sum, max_sum, i + 1, sum, false, false);
    }
    int up = isLimit ? num.charAt(i) - '0' : 9;
    for (int d = isNum ? 0 : 1; d <= up; ++d) {
      res += dfs(num, min_sum, max_sum, i + 1, sum + d, isLimit && d == up, true);
      res = res % mod;
    }
    if (!isLimit && isNum) {
      memo[i][sum] = res;
    }
    return res;
  }
}
