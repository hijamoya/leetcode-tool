import java.util.Arrays;

public class Floyd {
  // 求兩點之間倆倆的距離
  public int[][] distance(int n, int[][] edges) {
    int[][] w = new int[n][n];
    for (int[] row : w) {
      Arrays.fill(row, Integer.MAX_VALUE / 2); // 防止加法溢出
    }
    for (int[] e : edges) {
      int x = e[0], y = e[1], wt = e[2];
      w[x][y] = w[y][x] = wt;
    }

    int[][] dist = w;
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        }
      }
    }
    return dist;
  }
}
