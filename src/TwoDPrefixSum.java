public class TwoDPrefixSum {
  int[][] sum;
  public TwoDPrefixSum(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    // 与「一维前缀和」一样，前缀和数组下标从 1 开始，因此设定矩阵形状为 [n + 1][m + 1]（模板部分）
    sum = new int[m + 1][n + 1];
    // 预处理除前缀和数组（模板部分）
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int x1, int y1, int x2, int y2) {
    // 求某一段区域和 [i, j] 的模板是 sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];（模板部分）
    // 但由于我们源数组下标从 0 开始，因此要在模板的基础上进行 + 1
    x1++; y1++; x2++; y2++;
    return sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
  }
}
