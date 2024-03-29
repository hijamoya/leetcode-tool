import java.util.TreeSet;

public class BIT {
  private final int[] tree;

  // 注意，下標從1開始，所以傳進來的n實際上為n + 1
  public BIT(int n) {
    tree = new int[n];
    /*如果需要離散化
    TreeSet<Integer> set = new TreeSet<>();
    for (int n : nums) {
      set.add(n);
    }
    Map<Integer, Integer> idMap = new HashMap<>();
    // 下標從1開始
    int id = 1;
    for (int n : set) {
      idMap.put(n, id);
      id++;
    }
    */
  }

  // 将下标 i 上的数加一
  public void inc(int i) {
    while (i < tree.length) {
      tree[i]++;
      i += i & -i;
    }
  }

  // 返回闭区间 [1, i] 的元素和
  public int sum(int x) {
    int res = 0;
    while (x > 0) {
      res += tree[x];
      // 去掉最後一個位數
      x = (x & (x - 1));
    }
    return res;
  }

  // 返回闭区间 [left, right] 的元素和
  public int query(int left, int right) {
    return sum(right) - sum(left - 1);
  }
}
