import java.util.ArrayList;
import java.util.List;

public class TreeDiameter {
    int d1 = 0;
    int r1 = 0;

    int diameter(int[][] edges) {
        List<Integer>[] graph = new ArrayList[edges.length + 1];
        for (int i = 0; i <= edges.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        // 先從0出發找到最遠
        int[] r = dfs(graph, -1, 0, 0);
        // 再從最遠走回來，就是直徑
        return dfs(graph, -1, r[0], 0)[1];
    }

    int[] dfs(List<Integer>[] graph, int pa, int c, int d) {
        int[] ans = new int[]{c, d};
        for (int g : graph[c]) {
            if (g == pa) {
                continue;
            }
            int[] p = dfs(graph, c, g, d + 1);
            if (p[1] > ans[1]) {
                ans = new int[]{p[0], p[1]};
            }
        }
        return ans;
    }

    // 樹上dp作法
    private int dfs3(int x, int fa, List<Integer>[] g) {
        int maxLen = 0;
        for (int y : g[x]) {
            if (y != fa) {
                int subLen = dfs3(y, x, g) + 1;
                // res = Math.max(res, maxLen + subLen);
                maxLen = Math.max(maxLen, subLen);
            }
        }
        return maxLen;
    }
}
