import java.util.Arrays;

public class TreeAncestor {
  int[][] dp;

  public TreeAncestor(int n, int[] parent) {
    dp = new int[n][20];
    for (int i = 0; i < n; i++) {
      Arrays.fill(dp[i], -1);
      // 距離自己的2^0次方的parent，就是父節點
      dp[i][0] = parent[i];
    }
    // 預處理 每次以倍數往上調
    // 枚舉的時候要先枚舉j，因為我們要使用到j - 1的值
    // dp[i][j] = dp[dp[i][j - 1]][j - 1];
    // 19是看要枚舉到 2^幾次方
    for (int j = 1; j <= 19; j++) {
      for (int i = 0; i < n; i++) {
        if (dp[i][j - 1] != -1) {
          // 若是此節點存在
          dp[i][j] = dp[dp[i][j - 1]][j - 1];
        }
      }
    }
  }

  public int getKthAncestor(int node, int k) {
    // 查詢，例如要找5，我們先找2 ^ 0 在找 2^2
    // 所以要先找距離我1的，在找1的節點的距離4的 一路往上
    // 首先先取得二進位制的深度
    if (node == -1 || k == 0) {
      return node;
    }
    int lowbit = (k & -k) >> 1;
    int t = 0;
    while (lowbit > 0) {
      lowbit = lowbit >> 1;
      t++;
    }
    return getKthAncestor(dp[node][t], k & (k - 1));
  }
}
