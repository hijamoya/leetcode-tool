public class FenwickTree {
  int[] nums;
  int[] tree;

  FenwickTree(int[] nums) {
    this.nums = nums;
    this.tree = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      add(i + 1, nums[i]);
    }
  }

  int lowbit(int x) {
    return x & -x;
  }

  void add(int index, int x) {
    for (int i = index; i <= nums.length; i += lowbit(i)) {
      tree[i] += x;
    }
  }

  int query(int index) {
    int ans = 0;
    for (int i = index; i > 0; i-= lowbit(i)) {
      ans += tree[i];
    }
    return ans;
  }

  void update(int index, int val) {
    add(index + 1, val - nums[index]);
    nums[index] = val;
  }

  int sumRange(int left, int right) {
    return query(right + 1) - query(left);
  }
}
