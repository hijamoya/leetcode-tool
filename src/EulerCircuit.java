import java.util.*;

// https://leetcode.com/problems/valid-arrangement-of-pairs
// 歐拉路徑 - 一筆畫問題（一筆走完所有點）
// 歐拉迴路 - 走回原點
// https://oi-wiki.org/graph/euler/
public class EulerCircuit {
    Deque<Integer> ans = new LinkedList<>();

    public int[][] validArrangement(int[][] pairs) {
        // 歐拉路徑 - 一筆畫遊戲 這是半歐拉圖問題
        // 1. 建圖 2. 找出起點 3. dfs
        // 找出起點：a. 出度 = 入度 + 1 b. 若沒有就是任意點
        Map<Integer, Deque<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        Set<Integer> points = new HashSet<>();
        int start = 0;
        for (int[] p : pairs) {
            Deque<Integer> list = graph.getOrDefault(p[0], new LinkedList<>());
            list.add(p[1]);
            graph.put(p[0], list);
            inDegree.put(p[1], inDegree.getOrDefault(p[1], 0) + 1);
            points.add(p[0]);
            points.add(p[1]);
            start = p[0];
        }
        for (int p : points) {
            if (inDegree.getOrDefault(p, 0) + 1 == graph.getOrDefault(p, new LinkedList<>()).size()) {
                start = p;
                break;
            }
        }
        dfs(graph, start);
        // 最後要倒序建構答案
        int[][] res = new int[pairs.length][2];
        int last = ans.pop();
        for (int i = 0; i < res.length; i++) {
            res[i] = new int[]{last, ans.pop()};
            last = res[i][1];
        }
        return res;
    }

    void dfs(Map<Integer, Deque<Integer>> graph, int current) {
        Deque<Integer> stack = graph.getOrDefault(current, new LinkedList<>());
        while (!stack.isEmpty()) {
            dfs(graph, stack.pop());
        }
        // 全部pop完之後，才加入答案
        ans.push(current);
    }
}
