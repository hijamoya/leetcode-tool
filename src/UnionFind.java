class UnionFind {
  int[] parent;
  int[] rank;

  UnionFind(int[] parent) {
    this.parent = parent;
    rank = new int[parent.length];
  }

  int find(int x) {
    if (parent[x] == x) {
      return x;
    }
    parent[x] = find(parent[x]);
    return parent[x];
  }

  void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);
    if (rootX == rootY) {
      return;
    }
    if (rank[rootX] <= rank[rootY]) {
      parent[rootX] = rootY;
    } else {
      parent[rootY] = rootX;
    }
    if (rank[rootX] == rank[rootY]) {
      rank[rootY]++;
    }
  }
}