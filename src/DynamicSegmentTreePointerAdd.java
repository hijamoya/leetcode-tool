public class DynamicSegmentTreePointerAdd {
  private static class Node {
    int lazy, val;
    Node lChild, rChild;
  }

  int n;
  Node root;

  public DynamicSegmentTreePointerAdd(int n) {
    this.n = n;
    this.root = new Node();
  }

  public void add(int i, int x) { // 单点修改(驱动): 增量式 nums[i] += x
    add(i, x, 0, n - 1, root);
  }

  public void update(int i, int x) { // 单点修改(驱动): 覆盖式 nums[i] = x
    update(i, x, 0, n - 1, root);
  }

  public int query(int i) { // 单点查询 (驱动): 查询 nums[i]
    return query(i, 0, n - 1, root);
  }

  public void add(int l, int r, int x) { // 区间修改(驱动): 增量式 [l,r] 区间所有元素加上x
    add(l, r, x, 0, n - 1, root);
  }

  public int sum(int l, int r) { // 区间查询(驱动): nums[l]~nums[r]之和
    return sum(l, r, 0, n - 1, root);
  }

  public int min(int l, int r) { // 区间查询 (驱动): 查询[l,r]中的最小值
    return min(l, r, 0, n - 1, root);
  }

  public int max(int l, int r) { // 区间查询 (驱动): 查询[l,r]中的最大值
    return max(l, r, 0, n - 1, root);
  }

  // 单点修改: 增量式 令nums[idx] += x。修改叶子结点，无关标记。
  private void add(int idx, int x, int s, int t, Node cur) {
    if (s == t) {
      cur.val += x; // 增量更新
      return;
    }
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (idx <= c) add(idx, x, s, c, cur.lChild);
    else add(idx, x, c + 1, t, cur.rChild);
    pushUp(cur); // 后序动作，自底向上更新结点区间和 tree[i]
  }

  // 单点修改: 覆盖式 令nums[idx] = x。修改叶子结点，无关标记。
  private void update(int idx, int x, int s, int t, Node cur) {
    if (s == t) {
      cur.val = x; // 覆盖更新
      return;
    }
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (idx <= c) update(idx, x, s, c, cur.lChild);
    else update(idx, x, c + 1, t, cur.rChild);
    pushUp(cur);  // 后序动作，自底向上更新结点区间和 tree[i]
  }

  // 单点查询: 查询 nums[i]，尾递归
  private int query(int i, int s, int t, Node cur) {
    if (s == t) return cur.val;
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (i <= c) return query(i, s, c, cur.lChild);
    else return query(i, c + 1, t, cur.rChild);
  }

  // 区间修改: 增量式 [l,r] 区间所有元素加上x
  private void add(int l, int r, int x, int s, int t, Node cur) {
    if (l <= s && t <= r) { // 当前结点代表的区间在所求区间之内
      cur.val += (t - s + 1) * x; // 结点i的区间和加上t-s+1个x
      if (s != t) cur.lazy += x; // 结点i不是叶子结点，懒标记值加上x
      return;
    }
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (l <= c) add(l, r, x, s, c, cur.lChild);
    if (r > c) add(l, r, x, c + 1, t, cur.rChild);
    pushUp(cur); // 后序动作，自底向上更新结点区间和 tree[i]
  }

  // 区间查询: 求 nums[l]~nums[r]之和
  private int sum(int l, int r, int s, int t, Node cur) {
    if (l <= s && t <= r) return cur.val; // 当前结点代表的区间在所求区间之内
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2, sum = 0;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (l <= c) sum += sum(l, r, s, c, cur.lChild);
    if (r > c) sum += sum(l, r, c + 1, t, cur.rChild);
    return sum;
  }

  // 区间查询: 查询[l,r]中的最小值
  private int min(int l, int r, int s, int t, Node cur) {
    if (s == t) return cur.val; // 叶子结点
    addNode(cur); // 动态开点
    int c = s + (t - s) / 2, lmin = Integer.MAX_VALUE, rmin = Integer.MAX_VALUE;
    if (cur.lazy != 0) pushDown(s, c, t, cur); // 是否推送标记
    if (l <= c) lmin = min(l, r, s, c, cur.lChild);
    if (r > c) rmin = min(l, r, c + 1, t, cur.rChild);
    return Math.min(lmin, rmin);
  }

  // 区间查询: 查询[l,r]中的最大值
  private int max(int l, int r, int s, int t, Node cur) {
    if (s == t) return cur.val;
    addNode(cur);
    int c = s + (t - s) / 2, lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
    if (cur.lazy != 0) pushDown(s, c, t, cur);
    if (l <= c) lmax = max(l, r, s, c, cur.lChild);
    if (r > c) rmax = max(l, r, c + 1, t, cur.rChild);
    return Math.max(lmax, rmax);
  }

  // pushup: 更新 cur.val
  private void pushUp(Node cur) {
    Node lChild = cur.lChild, rChild = cur.rChild;
    cur.val = lChild.val + rChild.val;
  }

  // pushdown: 更新当前结点及其左右子结点的懒标记
  private void pushDown(int s, int c, int t, Node cur) {
    Node lChild = cur.lChild, rChild = cur.rChild;
    lChild.val += (c - s + 1) * cur.lazy; // 更新其左子结点的区间和
    lChild.lazy += cur.lazy; // 传递懒标记
    rChild.val += (t - c) * cur.lazy;
    rChild.lazy += cur.lazy;
    cur.lazy = 0; // 重置当前结点懒惰标记值
  }

  // 动态开点
  private void addNode(Node node) {
    if (node.lChild == null) node.lChild = new Node();
    if (node.rChild == null) node.rChild = new Node();
  }
}
