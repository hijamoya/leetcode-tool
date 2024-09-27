public class DynamicSegmentTreePointerAddMax {
    private class Node {
        int lazy, val, max;
        Node lChild, rChild;
    }

    int n;
    Node root;

    public DynamicSegmentTreePointerAddMax(int n) {
        this.n = n;
        this.root = new Node();
    }

    public void add(int l, int r, int x) { // 区间修改(驱动): 增量式 [l,r] 区间所有元素加上x
        add(l, r, x, 0, n - 1, root);
    }

    public int max(int l, int r) { // 区间查询 (驱动): 查询[l,r]中的最大值
        return max(l, r, 0, n - 1, root);
    }

    // 区间修改: 增量式 [l,r] 区间所有元素加上x
    private void add(int l, int r, int x, int s, int t, Node cur) {
        if (l <= s && t <= r) { // 当前结点代表的区间在所求区间之内
            cur.val += x; // 结点i的区间和加上t-s+1个x
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

    // 区间查询: 查询[l,r]中的最大值
    private int max(int l, int r, int s, int t, Node cur) {
        if (l <= s && t <= r) return cur.val;
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
        // TODO: 此處特化給max用
        cur.val = Math.max(lChild.val, rChild.val);
    }

    // pushdown: 更新当前结点及其左右子结点的懒标记
    private void pushDown(int s, int c, int t, Node cur) {
        Node lChild = cur.lChild, rChild = cur.rChild;
        lChild.val += cur.lazy; // 更新其左子结点的区间和
        lChild.lazy += cur.lazy; // 传递懒标记
        rChild.val += cur.lazy;
        rChild.lazy += cur.lazy;
        cur.lazy = 0; // 重置当前结点懒惰标记值
    }

    // 动态开点
    private void addNode(Node node) {
        if (node.lChild == null) node.lChild = new Node();
        if (node.rChild == null) node.rChild = new Node();
    }
}
