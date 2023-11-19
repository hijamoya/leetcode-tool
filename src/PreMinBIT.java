/**
 * 維護前綴最小值
 */
public class PreMinBIT {
  private final int[] tree;

  // 注意，下標從1開始，所以傳進來的n實際上為n + 1
  public PreMinBIT(int n) {
    tree = new int[n];
    for (int i = 0; i < n; i++) {
      tree[i] = Integer.MAX_VALUE;
    }
  }

  // 将下标 i 的位置的最大值改變
  public void update(int i, int val) {
    while (i < tree.length) {
      tree[i] = Math.min(tree[i], val);
      i += i & -i;
    }
  }

  // 返回闭区间 [1, i] 的最小元素 => 也就是前綴最小值
  public int preMin(int x) {
    int res = Integer.MAX_VALUE;
    while (x > 0) {
      res = Math.min(tree[x], res);
      // 去掉最後一個位數
      x = (x & (x - 1));
    }
    return res;
  }
}