// 求逆序對
// https://leetcode.cn/problems/k-inverse-pairs-array/description/
public class InversePair {
    int mod = (int) 1e9 + 7;
    Integer[][] memo;

    public int kInversePairs(int n, int k) {
        memo = new Integer[n + 1][k + 1];
        return dfs(n, k);
    }

    // 長度為n的恰好有k個逆序的個數
    int dfs(int n, int k) {
        if (k > n * (n - 1) / 2) {
            return 0;
        }
        if (n == 1) {
            return k == 0 ? 1 : 0;
        }
        if (memo[n][k] != null) {
            return memo[n][k];
        }
        long ans = 0;
        // 枚舉所有倒數位置
        // 最多 k - i個 必須小於n - 1個 因為逆序對每次最多產生n - 1個
        // k - i <= n - 1 => i >= k - n + 1
        // 例如我要算最多9個的逆序對，組數長度為5 能產生最多4個逆序對
        // 所以最多就是從5開始轉移 => 9 - 5 + 1
        for (int i = Math.max(0, k - n + 1); i <= k; i++) {
            ans += dfs(n - 1, i);
        }
        memo[n][k] = (int) (ans % mod);
        return (int) (ans % mod);
    }
}