import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
  List<int[]>[] graph;
  int n;

  public Dijkstra(int n, int[][] edges) {
    graph = new ArrayList[n];
    this.n = n;
    for (int i = 0; i < n; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int[] e : edges) {
      graph[e[0]].add(new int[]{e[1], e[2]});
    }
  }

  public void addEdge(int[] e) {
    graph[e[0]].add(new int[]{e[1], e[2]});
  }

  public int shortestPath(int t1, int t2) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[t1] = 0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    // 起始點，距離
    pq.offer(new int[]{t1, 0});
    while (!pq.isEmpty()) {
      int[] t = pq.poll();
      if (t[0] == t2) {
        return t[1];
      }
      if (t[1] > dist[t[0]]) {
        // 注意，如果一个节点 x 在出堆前，其最短路长度 dist[x] 被多次更新，那么堆中会有多个重复的 x
        // 并且包含 x 的二元组中的 dist[x] 是互不相同的（因为我们只在找到更小的最短路时才会把二元组入堆）。
        // 是用出堆的最短路值 dx(t[1]) 与当前的 dist[x] 比较，如果 dx > dist[x] 说明 x 之前出堆过
        // 我们已经更新了 x 的邻居的最短路，所以这次就不用更新了，继续外层循环。
        // 後面都是無謂的更新，因為只有最短的那個x有意義
        continue;
      }
      for (int[] g : graph[t[0]]) {
        int d = Math.min(dist[g[0]], dist[t[0]] + g[1]);
        // 只將最小距離的點加入，有助於減少計算量
        if (d < dist[g[0]]) {
          dist[g[0]] = d;
          pq.offer(new int[]{g[0], d});
        }
      }
    }
    return -1;
  }
}
