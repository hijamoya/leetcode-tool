import java.util.ArrayList;
import java.util.List;

public class ReRootDp {
  // problem 834
  private List<Integer>[] graph;
  private int[] ans, size;

  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    graph = new ArrayList[n]; // g[x] 表示 x 的所有邻居
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (var e : edges) {
      int x = e[0], y = e[1];
      graph[x].add(y);
      graph[y].add(x);
    }
    ans = new int[n];
    size = new int[n];
    dfs(0, -1, 0); // 0 没有父节点
    reroot(0, -1); // 0 没有父节点
    return ans;
  }

  private void dfs(int x, int fa, int depth) {
    // TODO: 先算出0的變化量
    ans[0] += depth; // depth 为 0 到 x 的距离
    size[x] = 1;
    for (int y : graph[x]) { // 遍历 x 的邻居 y
      if (y != fa) { // 避免访问父节点
        dfs(y, x, depth + 1); // x 是 y 的父节点
        size[x] += size[y]; // 累加 x 的儿子 y 的子树大小
      }
    }
  }

  private void reroot(int x, int fa) {
    for (int y : graph[x]) { // 遍历 x 的邻居 y
      if (y != fa) { // 避免访问父节点
        // TODO: 要改的就是這，計算變化量
        ans[y] = ans[x] + graph.length - 2 * size[y];
        reroot(y, x); // x 是 y 的父节点
      }
    }
  }
}
