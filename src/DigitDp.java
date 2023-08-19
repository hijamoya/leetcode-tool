public class DigitDp {
  int k;

  int dfs(Integer[][][][] memo, String num,int r, int i, int even, int odd, boolean isLimit, boolean isNum) {
    if (i == num.length()) {
      return isNum && even == odd && r == 0 ? 1 : 0; // isNum 为 true 表示得到了一个合法数字
    }
    if (!isLimit && isNum && memo[r][i][even][odd] != null) {
      return memo[r][i][even][odd];
    }
    int res = 0;
    if (!isNum) {
      // 可以跳过当前数位
      res = dfs(memo, num, r, i + 1, even, odd, false, false);
    }
    int up = isLimit ? num.charAt(i) - '0' : 9;
    for (int d = isNum ? 0 : 1; d <= up; ++d) {
      int newR = (r * 10 + d) % k;
      res += dfs(memo, num, newR, i + 1, even + (d % 2 == 0 ? 1 : 0), odd + (d % 2 == 1 ? 1 : 0), isLimit && d == up, true);
    }
    if (!isLimit && isNum) {
      // r的教訓
      memo[r][i][even][odd] = res;
    }
    return res;
  }
}
