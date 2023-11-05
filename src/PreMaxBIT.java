/**
 * 維護前綴最大值
 */
public class PreMaxBIT {
  private final int[] tree;

  // 注意，下標從1開始，所以傳進來的n實際上為n + 1
  public PreMaxBIT(int n) {
    tree = new int[n];
  }

  // 将下标 i 的位置的最大值改變
  public void update(int i, int val) {
    while (i < tree.length) {
      tree[i] = Math.max(tree[i], val);
      i += i & -i;
    }
  }

  // 返回闭区间 [1, i] 的最大元素 => 也就是前綴最大值
  public int preMax(int x) {
    int res = Integer.MIN_VALUE;
    while (x > 0) {
      res = Math.max(tree[x], res);
      // 去掉最後一個位數
      x = (x & (x - 1));
    }
    return res;
  }
}
