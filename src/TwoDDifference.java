public class TwoDDifference {
  int[][] diff;
  int m;
  int n;

  public TwoDDifference(int m, int n) {
    diff = new int[m + 2][n + 2];
    this.m = m;
    this.n = n;
  }

  void increase(int i1, int j1, int i2, int j2) {
    i1++;
    j1++;
    i2++;
    j2++;
    diff[i1][j1]++;
    diff[i1][j2 + 1]--;
    diff[i2 + 1][j1]--;
    diff[i2 + 1][j2 + 1]++;
  }

  void recover() {
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        diff[i + 1][j + 1] += diff[i + 1][j] + diff[i][j + 1] - diff[i][j];
      }
    }
    // grid[i][j]的資料存在diff[i + 1][j + 1]
  }
}
